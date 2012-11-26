package org.gnenc.yams.portlet.job;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.gnenc.yams.model.JobQueue;
import org.gnenc.yams.portlet.util.PortletUtil;
import org.gnenc.yams.portlet.util.PropsValues;
import org.gnenc.yams.service.JobQueueLocalServiceUtil;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;

/**
 * @author Drew A. Blessing
 * 
 * When ran, this job retrieves all email notice
 * jobs from the job queue table and sends email 
 * notices to those users who will be removed soon.
 */
public class RemoveAccountEmailNoticeJob implements MessageListener {

	public void receive(Message arg0) throws MessageListenerException {
		// Process jobs dated today.
		Date date = new Date();
		
		List<JobQueue> jobs = null;
		try {
			jobs = JobQueueLocalServiceUtil.getByJobDateAndJobAction(
					date, PortletUtil.JOB_ACTION_REMOVE_EMAIL_NOTICE);
		} catch (SystemException e1) {
			e1.printStackTrace();
		}
		
		for (JobQueue job : jobs) {
			List<JobQueue> removalJobs = null;
			try {
				removalJobs = JobQueueLocalServiceUtil.getByEmailAddressAndJobAction(
						job.getJobUserEmailAddress(), PortletUtil.JOB_ACTION_REMOVE);
				DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
				if (removalJobs.size() == 1) {
					MailEngine.send(PropsValues.JOB_FROM_EMAIL_ADDRESS, 
							job.getJobUserEmailAddress(), 
							PropsValues.JOB_REMOVE_NOTICE_EMAIL_SUBJECT, 
							PropsValues.JOB_REMOVE_NOTICE_EMAIL_BODY
							+ "\n\nRemoval date: " + df.format(removalJobs.get(0).getJobDate()));
					
				}
				// Job is complete - Remove the entry.
				JobQueueLocalServiceUtil.deleteJobQueue(job);
			} catch (MailEngineException e) {
				e.printStackTrace();
				// Do not delete from the database because it failed. Could we try again later?
			} catch (SystemException e) {
				e.printStackTrace();
				// Failed to remove entry.  Not much we can do.  Print and go on.
			}
		}
	}
}
