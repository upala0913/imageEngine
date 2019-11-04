new Vue({
    el: "#container",
    data: {
        message: "vue",
        tabPosition: "left"
    },
    methods: {
        btn_image: function() {
            window.location.href = "/upala/image/image.html"
        },
        btn_file: function() {
            alert("file");
        },
        btn_video: function() {
            alert("video");
        },
        btn_more: function() {
            alert("more");
        }
    }
});
