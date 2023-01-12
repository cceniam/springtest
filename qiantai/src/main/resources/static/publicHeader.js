console.log(".........publicHeader...........");
let publicHeader = new Vue({
    //element
    el: "#publicHeaderApp",
    data: {
        bookTypes: [],
        currentAccount:''
    },
    methods: {
        initBookTypes() {
            //局部变量,便于get方法中调用vue对象
            let _this = this;
            $.get("/booktype/all", function (data) {
                _this.bookTypes = data;
            })
        }
        ,
        initCurrentAccount(){
            let _this=this;
            $.get("/user/currentAccount",function (data) {
                _this.currentAccount=data;
            })
        }
    },
    //vue生命周期中调用
    created() {
        this.initBookTypes();
        this.initCurrentAccount();
    }
});
