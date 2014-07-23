(function () {
    'use strict';

    var app = angular.module('moniclj', ['ngRoute']);

    app.config(function ($routeProvider) {
        'use strict';

        $routeProvider
            .when('/', {
                console.log("Test1")
                templateUrl: 'views/overview.html'
            })
            .otherwise({ redirectTo: '/' });
    });

    app.factory('restService', function ($http) {

        var factory = {};

        factory.getChecks = function () {
console.log("Test1")
            return $http.get('/api/check');
        };

        return factory;

    });

    app.controller('Overview', function ($scope, restService) {

        $scope.checks = [];

        $scope.getChecks = function () {
            restService.getChecks()
                .success(function (results) {
                    if (results.length > 0) {
                        $scope.checks = results;
                    } else {
                        $scope.checks = [];
                    }
                });
        };

        $scope.getChecks();
console.log("Test1")
    });
})();
