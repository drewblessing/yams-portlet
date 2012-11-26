package org.gnenc.yams.portlet.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.JobQueue;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.portlet.ActionUtil;
import org.gnenc.yams.portlet.util.PortletUtil;
import org.gnenc.yams.service.AccountManagementService;
import org.gnenc.yams.service.JobQueueLocalServiceUtil;
import org.gnenc.yams.service.impl.AccountManagementServiceImpl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

/**
 * @author Drew A. Blessing
 * 
 * When ran, this job retrieves all account removal
 * jobs from the job queue table and takes the appropriate
 * action on the account.
 */
public class RemoveAccountJob implements MessageListener {

	public void receive(Message arg0) throws MessageListenerException {
		// Process jobs dated today.
		Date date = new Date();
		AccountManagementService ams = AccountManagementServiceImpl.getInstance();
		List<SubSystem> subsystems = new ArrayList<SubSystem>();
		subsystems.add(SubSystem.LDAP);
		
		List<JobQueue> jobs = null;
		try {
			jobs = JobQueueLocalServiceUtil.getByJobDateAndJobAction(
					date, PortletUtil.JOB_ACTION_REMOVE);
		} catch (SystemException e1) {
			e1.printStackTrace();
		}
		
		for (JobQueue job : jobs) {
			Account account = ActionUtil.accountFromEmailAddress(job.getJobUserEmailAddress());
			
			try {
				account.setAttribute("removeAccount", "TRUE");
				ams.modifyAccount(account, subsystems);
				JobQueueLocalServiceUtil.deleteJobQueue(job);
			} catch (ValidationException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// That's Ok
			} catch (SystemException e) {
				e.printStackTrace();
				// Failed to remove entry.  Not much we can do.  Print and go on.
			}
		}
	}

}
