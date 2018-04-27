angular.module("myapp", []).controller("myapp", function ($http, $scope) {
    let Agents = ["Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod"]
    let tag = false
    for (var v = 0; v < Agents.length; v++) {
        if (navigator.userAgent.indexOf(Agents[v]) > 0) {
            document.body.innerHTML = "很抱歉，该页面无法使用手机访问"
            return
        }
    }
    $scope.DivLeft = (window.innerWidth - 1024) / 2
    $scope.DivTop = (window.innerHeight - 500) / 2
    $scope.MatchId = null
    $scope.PlayerManager = []
    $scope.BattleManager = []
    $scope.PlayerManagerTemp
    $scope.BattleManagerTemp
    setTimeout(function () {
        Intertimes($http, $scope)
    }, 50)
    $scope.clearTime = setInterval(function () {
        Intertimes($http, $scope)
    }, 2000)

})
let Intertimes = ($http, $scope) => {
    console.log($scope.isHidesmain1)
    console.log($scope.isHidesmain2)
    $http.get("/vote/BattleGetTrue").then(function (obj) {
        console.log(obj.data)
        if (obj.data == null || obj.data == undefined || obj.data.length == 0) {
            $http.get("/vote/BattleGetISTrue").
            then(function (obj) {
                if (obj.data != null && obj.data.length != 0 && obj.data != undefined) {
                    $scope.isHidesmain1 = "none"
                    $scope.isHidesmain2 = "none"
                    $scope.isHidesmain3 = "block"
                    obj.data.needscorecount = (obj.data.tickettruecount*.4+obj.data.needscorecount*.6-obj.data.ticketfalsecount*1.1).toFixed(2)
                    if(obj.data.needscorecount<0)obj.data.needscorecount=0
                    $scope.BattleManagerTemp = obj.data
                    $http.post("/vote/PlayerGetinfo", {
                        playerid: $scope.BattleManagerTemp.playerid
                    }).
                    then(function (obj) {
                        if (obj.data != null) {
                            $scope.PlayerManagerTemp = obj.data
                        }
                    })
                }else{
                    //当前没有比赛
                    $scope.isHidesmain1 = "none"
                    $scope.isHidesmain2 = "block"
                    $scope.isHidesmain3 = "none"
                }
            })
        } else {
            $scope.isHidesmain1 = "block"
            $scope.isHidesmain2 = "none"
            $scope.isHidesmain3 = "none"
            //当前正在比赛并得到比赛数据
            $scope.BattleManager = obj.data
            $scope.MatchId = obj.data[0].MatchId
            for (let temp in $scope.BattleManager) {
                $http.post("/vote/PlayerGetinfo", {
                    playerid: $scope.BattleManager[temp].playerid
                }).
                then(function (obj) {
                    if (obj.data != null) {
                        $scope.PlayerManager[temp] = obj.data
                    }
                })
            }
        }
    })
}