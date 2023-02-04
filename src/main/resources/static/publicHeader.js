console.log("加载公共头部vue对象");
let publicHeader = new Vue({
    //element
    el: "#publicHeaderApp",
    data: {
        // 数据类型数组
        bookTypes: [],
        // 当前登陆的账户
        currentAccount:''
    },
    methods: {
        // 获取书籍类型
        initBookTypes() {
            //局部变量,便于get方法中调用vue对象
            let _this = this;
            // 发起请求
            $.get("/booktype/all", function (data) {
                _this.bookTypes = data;
            })
        }
        ,
        initCurrentAccount(){
            let _this=this;
            $.get("/user/currentAccount",function (data) {//data为返回数据
                _this.currentAccount=data;//将data赋值给Vue data中的currentAccount
            })
        }
    },
    //vue生命周期中调用,创建对象时调用
    created() {
        // 调用方法
        this.initBookTypes();
        this.initCurrentAccount();
    }
});
