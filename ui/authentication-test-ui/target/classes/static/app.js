new Vue({
   el: '#auth-test-ui-app',

   data: {
      adminResourceStatus: 'not implemented',
      userResourceStatus: ''
   },

   created: function () {
      this.getUserResourceStatus();
      this.getAdminResourceStatus();
   },

   methods: {
      getUserResourceStatus: function () {
         this.userResourceStatus = 'checking';

         this.getJson('proxy/protected-rest-resource/api/secured/user')
            .then(function (data) {
               this.userResourceStatus = data.status;
            }.bind(this));
      },

      getAdminResourceStatus: function () {
         this.adminResourceStatus = 'checking';

         this.getJson('proxy/protected-rest-resource/api/secured/admin')
            .then(function (data) {
               this.adminResourceStatus = data.status;
            }.bind(this));
      },

      getJson: function (url) {
         return new Promise(function (resolve, reject) {
            $.ajax({
               type: 'GET',
               url: url,
               async: true,
               contentType: 'application/json',
               success: resolve,
               error: function (jqXHR, textStatus, errorThrown) {
                  reject(this.handleFailure({
                     jqXHR: jqXHR,
                     textStatus: textStatus,
                     errorThrown: errorThrown
                  }, null));
               }.bind(this)
            });
         }.bind(this));
      },

      handleFailure: function (xhrResponse, responseJson) {
         var parms = {};

         if (!responseJson && xhrResponse && xhrResponse.jqXHR && xhrResponse.jqXHR.responseJSON) {
            parms.json = responseJson = xhrResponse.jqXHR.responseJSON;
         }

         if (responseJson && responseJson.message) {
            parms.errorMessage = responseJson.message;
         }

         if (xhrResponse) {
            parms.xhrStatus = xhrResponse.textStatus;
            parms.xhrError = xhrResponse.errorThrown;

            if (xhrResponse.jqXHR && xhrResponse.jqXHR.status) {
               parms.statusCode = xhrResponse.jqXHR.status;

               if (parms.xhrStatus === 'error') {
                  parms.errorMessage = 'Http status code ' + parms.statusCode + ' received.';
               }
            }
         }

         return parms;
      }
   }
});
