AUI().use(
	'aui-base',
	'aui-dialog',
	'aui-io-plugin',
	function(A) {
		Liferay.namespace('YAMS');

		Liferay.YAMS = {
			test: function() {
				alert(this._popup);
			},
			init: function(param) {
				var instance = this;
			},

			closePopup: function() {
				var instance = this;

				instance.getPopup().hide();
			},

			displayPopup: function(url, title) {
				var instance = this;

				var viewportRegion = A.getBody().get('viewportRegion');

				var popup = instance.getPopup();

				popup.show();

				popup.set('title', title);

				popup.io.set('uri', url);
				popup.io.start();
			},

			getPopup: function() {
				var instance = this;

				if (!instance._popup) {
					instance._popup = new A.Dialog(
						{
							align: {
								node: null,
								points: ['tc', 'tc']
							},
							constrain2view: true,
							cssClass: 'yams-portlet-dialog',
							modal: true,
							resizable: false,
							width: 600
						}
					).plug(
						A.Plugin.IO,
						{autoLoad: false}
					).render();
				}

				instance._popup.io.set('form', null);
				instance._popup.io.set('uri', null);

				return instance._popup;
			},
		}
	}
);