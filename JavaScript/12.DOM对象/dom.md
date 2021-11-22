# DOM
## DOM：文档对象模型
## 核心
### 浏览器网页就是一个Dom树形结构
* 更新：更新DOM节点
* 遍历DOM节点：得到DOM节点
* 删除：删除节点
* 添加：添加一个节点
```javascript
   // 获取DOM节点
    var h1 = document.getElementsByTagName('h1');
    var p1 = document.getElementById('p1');
    var p2 = document.getElementsByClassName('p2');
    var father = document.getElementById('father')
    var childrens = father.children;
    var firtstchildren = father.firstChild;
    var lastchildren = father.lastChild;
//    这些都是原生代码，之后用jQuery();
```
## 更新节点
```javascript
//更新节点 操作节点
    var id1 = document.getElementById('id1');
    id1.innerText = 'wsk is th king!'//修改文本值，无法转义
    id1.innerHTML = '<strong>wsk is th king!!!</strong>';//可以转义
    id1.style.color = 'red';
    id1.style.fontSize = '100px';//- 转 驼峰命名
```
## 删除节点
```javascript
//删除节点
    //删除节点的步骤： 先获取父节点，再通过父节点删除节点
    father.removeChild(p1)
    //删除时一个动态的过程，children时刻都在变化
    father.removeChild(father.children[0])
    father.removeChild(father.children[0])
    father.removeChild(father.children[0])
```
## 插入节点
```javascript

```