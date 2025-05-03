/*(function($) {
    "use strict";

    var registry = window.adaptTo("foundation-registry");

    registry.register("foundation.validation.validator", {
        selector: "[data-validation=geeks-multifield]",
        validate: function(element) {
            var el = $(element);
            var max = parseInt(el.data("max-items"), 10);
            var min = parseInt(el.data("min-items"), 10);

            var items = el[0].items ? el[0].items.length : el.children("coral-multifield-item").length;

            console.log("Max:", max, "Min:", min, "Current:", items);

            if (max && items > max) {
                return "You can add only " + max + " navItems.";
            }
            if (min && items < min) {
                return "You have to add at least " + min + " navItems.";
            }
        }
    });
})(jQuery);
*/
(function($,Coral) {
    "use strict";

        var registry = $(window).adaptTo("foundation-registry");

        registry.register("foundation.validation.validator", {
            selector: "[data-validation=geeks-multifield]",
            validate: function(element) {
                var el = $(element);
                console.log(el);
            }
        });
})(jQuery,Coral);

//(function($, Coral) {
//    "use strict";
//
//    var registry = $(window).adaptTo("foundation-registry");
//
//    registry.register("foundation.validation.validator", {
//        selector: "[data-validation=geeks-multifield]",
//        validate: function(element) {
//            var el = $(element);
//            var max = parseInt(el.data("max-items"), 10);
//            var min = parseInt(el.data("min-items"), 10);
//
//            var items = el[0].items ? el[0].items.length : el.children("coral-multifield-item").length;
//
//            console.log("Max:", max, "Min:", min, "Current:", items);
//
//            if (max && items > max) {
//                return "You can add only " + max + " navItems.";
//            }
//            if (min && items < min) {
//                return "You have to add at least " + min + " navItems.";
//            }
//        }
//    });
//})(jQuery, Coral);