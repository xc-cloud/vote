let exit = $('.exit')
exit.hide()
angular.module("myapp", ["ngRoute"]).
config(function ($routeProvider, $locationProvider) {
        $locationProvider.hashPrefix('!')
        $routeProvider.
        when("/wap", { //WAP端默认页面
            templateUrl: 'view/wap/index.html',
            controller: "wap"
        }).
        when("/login", { //登录页面
            templateUrl: 'view/pc/login.html',
            controller: "login"
        }).
        when("/Register", { //注册页面
            templateUrl: 'view/pc/register.html',
            controller: "Register"
        }).
        when("/Manager", { //比赛管理页面
            templateUrl: 'view/pc/manager.html',
            controller: "Manager"
        }).
        when("/MatchManager", { //比赛管理页面
            templateUrl: 'view/pc/MatchManager.html',
            controller: "MatchManager"
        }).
        when("/PlayerManager", { //比赛选手管理页面
            templateUrl: 'view/pc/PlayerManager.html',
            controller: "PlayerManager"
        }).
        when("/BattleManager", { //比赛场次管理页面
            templateUrl: 'view/pc/BattleManager.html',
            controller: "BattleManager"
        }).
        when("/PlayerTicket", { //比赛投票页面
            templateUrl: 'view/wap/PlayerTicket.html',
            controller: "PlayerTicket"
        }).
        otherwise({
            templateUrl: 'view/pc/error.html'
        })
    })
    .controller("myapp", function ($scope,$http, $location, $window) {
        $scope.userdata = null
        panelDefault($scope)
        bodyBackground($scope, "url('images/background.png') repeat")
        getURL($location)
        $scope.data = []
        $scope.temp = {}
        $scope.exit = () => {
            $http.get("/vote/exit").then(function(obj){
                if(obj.data){
                    $location.path("/login")
                    exit.hide()
                }
            })
        }
    })
    .controller("login", function ($scope, $location, $window, $routeParams, $http, $log) {
        //pc端处理事件
        $scope.data = {username:"",password:""}
        $window.document.title = "K歌"
        ManagerIsLogin($http, $location,$scope)//判断管理员是否已经登录
        $scope.submits = () => {//管理员登录验证
            ManagerLogin($http, $scope, $location)
        }
        //        $http.get("/vote/getUserSize")获取在线人数
        //        .then(function(obj){
        //        	alert(obj.data)
        //        })
        //判断用户是否已经登录，如果登录，将从服务器获取数据
    })
    .controller("wap", function ($scope, $http, $location, $window, $routeParams) {
        //wap端处理事件
        UserIsLogin($http, $location,$scope)
        $scope.data = {username:"",password:""}
        $scope.submits = () => {
            UserLogin($http, $scope, $location)
        }

    })
    .controller("Register", function ($scope, $location, $window, $routeParams, $http) {
        //用户注册界面
        $scope.data = {
            username: "",
            password: "",
            name: "",
            sex: 1,
            tel: ""
        }
        $scope.radio = (obj) => {
            $scope.data.sex = obj ? 1 : 0
        }
        $scope.submitRegister = () => {//用户注册
            $http.post("/vote/UsersRegister", $scope.data).
            then(function (obj) {
                if (obj.data)
                    alert("注册成功，请使用手机登录！")
                else
                    alert("注册失败！")
            })
            console.log($scope.data)
        }
    })
    .controller("Manager", function ($scope) {
    
    })
    .controller("MatchManager", function ($scope, $http) {
        $scope.isshow = false
        MatchManager.get($http,$scope,false)//得到所有数据
        //点击修改信息按钮
        $scope.updateMatch = (id, matchid) => {
            $scope.thisId = id
            ViewUpdate(false,$scope)
            MatchManager.get($http,$scope,false,{"matchid": matchid})
        }
        $scope.deleteMatch = (id, matchid) => {
            MatchManager.delete($http,$scope,{"matchid": matchid},id)
        }
        $scope.insertMatch = () => {
            MatchManager.set($http,$scope,$scope.MatchManagerTemp)
        }
        $scope.close = () => {
            $scope.isshow = false
            $scope.MatchManagerTemp = {}
        }
        $scope.open = () => {
            ViewUpdate(true,$scope)
        }

    }).
    controller("PlayerManager", function ($scope, $http) {
        $scope.isshow = false
        PlayerManager.get($http,$scope,false)//得到所有数据
        //点击修改信息按钮
        $scope.updatePlayer = (id, playerid) => {
            $scope.thisId = id
            ViewUpdate(false,$scope)
            PlayerManager.get($http,$scope,false,{"playerid": playerid})
        }
        $scope.deletePlayer = (id, matchid) => {
            PlayerManager.delete($http,$scope,{"playerid": matchid},id)
        }
        $scope.insertPlayer = () => {
            PlayerManager.set($http,$scope,$scope.PlayerManagerTemp)
        }
        $scope.close = () => {
            $scope.isshow = false
            $scope.PlayerManagerTemp = {}
        }
        $scope.open = () => {
            ViewUpdate(true,$scope)
        }
    }).
    controller("BattleManager", function ($scope, $http) {
        $scope.isshow = false
        BattleManager.get($http,$scope)//得到所有数据
        MatchManager.get($http,$scope,true)//得到比赛状态所有信息
        //点击修改信息按钮
        $scope.updateBattle = (id, battleid) => {
            $scope.thisId = id
            ViewUpdate(false,$scope)
            BattleManager.get($http,$scope,false,{"battleid": battleid})
            $scope.getData()
        }
        $scope.deleteBattle = (id, battleid) => {
            BattleManager.delete($http,$scope,{"battleid": battleid},id)
        }
        $scope.insertBattle = () => {
            BattleManager.set($http,$scope,$scope.BattleManagerTemp)
        }
        $scope.close = () => {
            $scope.isshow = false
            $scope.BattleManagerTemp = {}
        }
        $scope.open = () => {
            ViewUpdate(true,$scope)
            $scope.getData()
        }
        $scope.getData = () => {
            MatchManager.get($http,$scope,true)//得到比赛状态所有信息
            PlayerManager.get($http,$scope,true)//得到比赛状态所有信息
            GetMatchType.get($http,$scope,true)
            GetBattleflagType.get($http,$scope,true)
        }
        $scope.startMatch = () => {
            BattleManager.updateMatch($http,$scope,[2,$scope.MatchId])
        }
        $scope.stopISMatch = () => {
            BattleManager.updateMatch($http,$scope,[3,$scope.MatchId])
        }
        $scope.stopMatch = () => {
            BattleManager.updateMatch($http,$scope,[4,$scope.MatchId])
        }
    }).
    controller("PlayerTicket", function ($scope, $http) {
        $scope.selectAllOrderby = []
        $scope.OrderTemp = []
        $scope.selectAllUser=[]
        $http.get("/vote/selectAllOrderby").
        then(function(obj){
            if(obj.data!=null){
                let i=0;
                for(let temp in obj.data){
                    if($scope.OrderTemp.indexOf(obj.data[temp].matchid)>=0)
                    continue;
                    else{
                            $scope.OrderTemp.push(obj.data[temp].matchid)
                            $scope.selectAllOrderby[i++] = Array()
                        }
                }
                for(let temp in obj.data){ $scope.selectAllOrderby[$scope.OrderTemp.indexOf(obj.data[temp].matchid)].push(obj.data[temp])
                    $http.post("/vote/PlayerGetinfo", {playerid:obj.data[temp].playerid}).
                        then(function (obj) {
                        if(obj.data!=null){
                            $scope.selectAllUser.push(obj.data)
                            console.log($scope.selectAllUser)
                        }
                    })
                }
            }
        })
        $scope.TicketTrue = (id,temp,matchid,battleid,playerid) => {
        	console.log({
                matchid:matchid,
                battleid:battleid,
                playerid:playerid,
                ticketbol:1,
            })
            $http.post("/vote/PlayerTicket",{
                matchid:matchid,
                battleid:battleid,
                playerid:playerid,
                ticketbol:1,
            }).
            then(function(obj){
                switch(obj.data){
                    case -1:alert("服务器拒绝了你的投票请求");break;
                    case 1:{
                        alert("投票成功！");
                        $scope.selectAllOrderby[id][temp].tickettruecount +=1 ;
                    }break;
                    default :alert("你已投过票了，请勿重复投票")
                }
            })
        }
        $scope.TicketFlase = (id,temp,matchid,battleid,playerid) => {
            $http.post("/vote/PlayerTicket",{
                matchid:matchid,
                battleid:battleid,
                playerid:playerid,
                ticketbol:0,
            }).
            then(function(obj){
                switch(obj.data){
                    case -1:alert("服务器拒绝了你的投票请求");break;
                    case 1:{
                        alert("投票成功！");
                        $scope.selectAllOrderby[id][temp].ticketfalsecount +=1 ;
                    }break;
                    default :alert("你已投过票了，请勿重复投票")
                }
            })
        }
    })
//设置页面默认值
let panelDefault = ($scope) => {
    $scope.windowTop = (window.innerHeight - window.innerHeight * .8) / 2
    $scope.windowHeight = window.innerHeight * .8
    $scope.windowH = window.innerHeight
}

//设置页面背景
let bodyBackground = ($scope, color) => {
    $scope.bodyBackground = color
}

//更新新增或修改页面
let ViewUpdate = (obj,$scope) => {
    if(obj){
        $scope.isshow = true
        $scope.btnInsert = true
        $scope.btnUpdate = false
    }else{
        $scope.isshow = true
        $scope.btnInsert = false
        $scope.btnUpdate = true
    }
}
//根据浏览器跳转指定页面
let getURL = ($location, text) => {
    if (text != undefined) {
        $location.path(text)
        return
    }
    let Agents = ["Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod"]
    let tag = false
    for (var v = 0; v < Agents.length; v++) {
        if (navigator.userAgent.indexOf(Agents[v]) > 0) {
            tag = true;
            break;
        }
    }
    tag ? $location.path("/wap") : $location.path("/login")
}



//判断管理员是否已经登录
let ManagerIsLogin = ($http, $location,$scope) => {
    $http.get("/vote/isLogin")
        .then(function (obj) {
            if (obj.data) { //管理员已经登录，那么将跳转到管理页面
                exit.show()
                $location.path("/MatchManager")
            }
        })
}

//管理员登录验证
let ManagerLogin = ($http, $scope, $location) => {
    $http.post("/vote/managerLogin", $scope.data).
    then(function (obj) {
        if (obj.data[0] == "userisonline")
            alert("您的账号已在别处登录，请勿重复登录，如果您的账号疑似被盗，请立即修改密码！")
        else if (obj.data[0] == "scaleisnull")
            alert("很抱歉，您的账号无法在电脑端登录！")
        else if (!eval(obj.data[0]))
            alert("您输入的用户名或密码错误！")
        else{
            exit.show()
            $location.path("/MatchManager")
        }
    })
}

//判断用户是否已经登录
let UserIsLogin = ($http, $location,$scope) => {
    $http.get("/vote/isLogin")
        .then(function (obj) {
            if (obj.data) { //管理员已经登录，那么将跳转到管理页面
                exit.show()
                $location.path("/PlayerTicket")
            }
        })
}

//用户登录验证
let UserLogin = ($http, $scope, $location) => {
    $http.post("/vote/UserLogin", $scope.data).
    then(function (obj) {
        if (obj.data[0] == "userisonline")
            alert("您的账号已在别处登录，请勿重复登录，如果您的账号疑似被盗，请立即修改密码！")
        else if (!eval(obj.data[0]))
            alert("您输入的用户名或密码错误！")
        else{
            exit.show()
            $location.path("/PlayerTicket")
        }
    })
}
//MatchManager
let MatchManager = {
    get: function ($http, $scope,objs, data) {
        if (data != undefined) {
            $http.post("/vote/MatchGetinfo", data).
            then(function (obj) {
                if (obj.data != null){
                    if(objs)
                    for (let temp in data) {
                    data[temp].matchname = data[temp].matchid + "(" + data[temp].matchname + ")"
                    }
                    $scope.MatchManagerTemp = obj.data
                }
                else
                    alert("获取该条信息失败")
            })
        } else {
            $http.get("/vote/MatchManager").
            then(function (obj) {
                console.log(obj.data)
                if (obj.data != null){
                    if(objs)
                        for (let temp in obj.data)
                            obj.data[temp].matchname = obj.data[temp].matchid + "(" + obj.data[temp].matchname + ")"
                    $scope.MatchManager = obj.data
                }
                console.log($scope.MatchManager)
            })
        }

    },
    set: function ($http, $scope, data) {
        console.log(data)
        $http.post("/vote/MatchaddInfo", data)
            .then(function (obj) {
                if (obj.data == 0) {
                    //修改成功
                    $scope.MatchManager[$scope.thisId] = data
                    alert("修改成功！")
                    $scope.close()
                } else if (obj.data == -1) {
                    //修改或添加失败
                    alert("处理该条信息失败!")
                } else {
                    $scope.MatchManagerTemp.matchid = obj.data
                    $scope.MatchManager.push(data)
                    alert("添加成功！")
                    $scope.close()
                    //添加成功
                }
            })
    },
    delete: function ($http, $scope, data, id) {
        console.log(data)
        $http.post("/vote/Matchdelinfo", data)
            .then(function (obj) {
                if (obj.data) {
                    $scope.MatchManager.splice(id, 1)
                    alert("删除成功")
                } else
                    alert("删除失败!")
            })
    }
}
//PlayerManager
let PlayerManager = {
    get: function ($http, $scope,objs, data) {
        if (data != undefined) {
            $http.post("/vote/PlayerGetinfo", data).
            then(function (obj) {
                if (obj.data != null){
                    if(objs)
                    for (let temp in obj.data) {
                        obj.data[temp].playername = obj.data[temp].playerid + "(" + obj.data[temp].playername + ")"
                    }
                    $scope.PlayerManagerTemp = obj.data
                }
                else
                    alert("获取该条信息失败")
            })
        } else {
            $http.get("/vote/PlayerManager").
            then(function (obj) {
                if (obj.data != null){
                    if(objs)
                    for (let temp in obj.data) {
                        obj.data[temp].playername = obj.data[temp].playerid + "(" + obj.data[temp].playername + ")"
                    }
                    $scope.PlayerManager = obj.data
                }
            })
        }

    },
    set: function ($http, $scope, data) {
        $http.post("/vote/PlayeraddInfo", data)
            .then(function (obj) {
                if (obj.data == 0) {
                    //修改成功  
                    $scope.PlayerManager[$scope.thisId] = data
                    alert("修改成功！")
                    $scope.close()
                } else if (obj.data == -1) {
                    //修改或添加失败
                    alert("处理该条信息失败!")
                } else {
                    data.playerid = obj.data
                    $scope.PlayerManager.push(data)
                    alert("添加成功！")
                    $scope.close()
                    //添加成功
                }
            })
    },
    delete: function ($http, $scope, data, id) {
        console.log(data)
        $http.post("/vote/Playerdelinfo", data)
            .then(function (obj) {
                if (obj.data) {
                    $scope.PlayerManager.splice(id, 1)
                    alert("删除成功")
                } else
                    alert("删除失败!")
            })
    }
}
//BattleManager
let BattleManager = {
    get: function ($http, $scope,objs, data) {
        if (data != undefined) {
            $http.post("/vote/BattleGetinfo", data).
            then(function (obj) {
                if (obj.data != null){
                    $scope.BattleManagerTemp = obj.data
                }
                else
                    alert("获取该条信息失败")
            })
        } else {
            $http.get("/vote/BattleManager").
            then(function (obj) {
                console.log(obj.data)
                if (obj.data != null){
                    $scope.BattleManager = obj.data
                } 
            })
        }

    },
    set: function ($http, $scope, data) {
        $http.post("/vote/BattleaddInfo", data)
            .then(function (obj) {
                if (obj.data == 0) {
                    //修改成功  
                    $scope.BattleManager[$scope.thisId] = data
                    alert("修改成功！")
                    $scope.close()
                } else if (obj.data == -1) {
                    //修改或添加失败
                    alert("处理该条信息失败!")
                } else {
                    data.battleid = obj.data
                    $scope.BattleManager.push(data)
                    alert("添加成功！")
                    $scope.close()
                    //添加成功
                }
            })
    },
    delete: function ($http, $scope, data, id) {
        $http.post("/vote/Battledelinfo", data)
            .then(function (obj) {
                if (obj.data) {
                    $scope.BattleManager.splice(id, 1)
                    alert("删除成功")
                } else
                    alert("删除失败!")
            })
    },
    updateMatch:function($http, $scope, data){
        $http.post("/vote/UpdateMatch", data)
            .then(function (obj) {
                if (obj.data) {
                    alert("操作成功")
                    BattleManager.get($http,$scope,false)
                } else
                    alert("操作失败!")
            })
    }
}
let GetMatchType = {
    get: function ($http, $scope, objs) {
        $http.get("/vote/GetMatchType").
        then(function (obj) {
            console.log(obj.data)
            if (obj.data != null) {
                if (objs)
                    for (let temp in obj.data) {
                        obj.data[temp].matchtype = obj.data[temp].id + "(" + obj.data[temp].matchtype + ")"
                    }
                $scope.GetMatchType = obj.data
            }
        })
    }
}
let GetBattleflagType = {
    get: function ($http, $scope, objs) {
        $http.get("/vote/GetBattleflagType").
        then(function (obj) {
            console.log(obj.data)
            if (obj.data != null) {
                if (objs)
                    for (let temp in obj.data) {
                        obj.data[temp].type = obj.data[temp].id + "(" + obj.data[temp].type + ")"
                    }
                $scope.GetBattleflagType = obj.data
            }
        })
    }
}