(function () {
    'use strict';

    var app = angular.module('moniclj', ['ngRoute']);

    app.config(function ($routeProvider) {

        $routeProvider
            .when('/', {
                controller: 'Overview',
                templateUrl: 'app/views/overview.html'
            })
            .otherwise({ redirectTo: '/' });
    });

    app.factory('restService', function ($http) {

        var factory = {};

        factory.getChecks = function () {
            return $http.get('/api/check');
        };

        return factory;

    });

    app.controller('Overview', function ($scope, restService) {

        $scope.checks = [];

        $scope.getChecks = function () {
            restService.getChecks()
                .success(function (results) {
                    if (results.length) {
                        $scope.checks = results;
                    } else {
                        $scope.checks = [];
                    }
                });
        };

        $scope.getChecks();

    });

})();
