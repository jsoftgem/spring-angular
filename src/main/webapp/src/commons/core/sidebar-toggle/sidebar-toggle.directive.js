(function () {
    'use strict';

    angular.module('sidebarToggle.directive', [])
        .directive('toggleSidebar', ToggleSidebar);

    function ToggleSidebar() {
        return {
            restrict: "A",
            link: function (scope, element, attr) {
                element.addClass('sidebar-toggle-button');
                element.unbind('click');
                element.click(function () {
                    angular.element("#wrapper").toggleClass('toggled');
                });
            }
        };
    }
})();
