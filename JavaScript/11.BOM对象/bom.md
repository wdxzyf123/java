# BOM
### BOM：浏览器对象模型（重点）

浏览器内核

1. IE 6 ~ 11
2. Chrome
3. Sfari
4. FireFox(linux)
## window代表浏览器窗口
```javascript
window.alert()
window.innerHeight
window.outerHeight
```
---
## Navigator
Navigator（是浏览器）,封装了浏览器的信息,一般我们不使用navigator对象，因为他可以人为更改
```javascript
navigator.appName
"Netscape"
navigator.appVersion
"5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36"
navigator.userAgent
"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36"
navigator.platform
"Win32"
```
## screen 屏幕尺寸
```javascript
screen.width
1080
screen.height
1920
```
## location (重要) 代表当前页面的URL信息
```javascript
hash: ""
host: "www.baidu.com" //主机
hostname: "www.baidu.com"
href: "https://www.baidu.com/"//跳转地址
origin: "https://www.baidu.com"
pathname: "/"
port: ""
protocol: "https:"//协议
reload: ƒ reload()//刷新
//设置新地址
assign: ƒ assign("新的url")
```
## document代表当前页面，HTML DOM文档树

```javascript
document.title
"百度一下，你就知道"
document.title="wsk"
"wsk"

var dl = document.getElementById('app')//获取html的id节点
```
document可以获取cookie
```javascript
document.cookie
"BIDUPSID=9ED0D1A21E89B289F37B825D39E32927; PSTM=1610339741; BD_UPN=12314753; BAIDUID=E27CF7317171625FEB57895B53B8F9AB:FG=1; sugstore=0; BDORZ=FFFB88E999055A3F8A630C64834BD6D0; BAIDUID_BFESS=E27CF7317171625FEB57895B53B8F9AB:FG=1; delPer=0; BD_CK_SAM=1; PSINO=6; BDRCVFR[n9IS1zhFc9f]=mk3SLVN4HKm; H_PS_645EC=6e6csrPC0lbB8FQyUQ8U9oEX4k%2FizzQWQNhbS0cmJM6So0w7squxkUaJaf6X1v2fKstIgCfAWPLB; BD_HOME=1; H_PS_PSSID=33802_33814_31254_33848_33675_33894_33810; BA_HECTOR=8h852084808k0ga0pq1g82ot40q"
```
劫持cookie
```javascript
<script src="jiechi.js"></script>
// 非法人员获取你的cookie上传到服务器
```
服务器端可以设置cookie:httpOnly
## history
```javascript
history.back()
history.forward()
```








































