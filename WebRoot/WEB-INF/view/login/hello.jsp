<!doctype html>
<script>
var rootpath;
function getRootPath(){
var strFullPath=window.document.location.href;
var strPath=window.document.location.pathname;
var pos=strFullPath.indexOf(strPath);
var prePath=strFullPath.substring(0,pos);
var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
return(prePath+postPath);
}
rootpath = getRootPath();

</script>
<html ng-app>
    <head>
         <script src="/springtest/js/angular.min.js"></script>
         <script src="/springtest/controller/login/helloCtrl.js"></script>
    </head>
    <body ng-controller="testCtrl">
        Your name: <input type="text" ng-model="yourname" placeholder="World">
        <hr>
        Hello {{loginUser || '1223World'}}!
    </body>
</html>

