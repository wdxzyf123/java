# Git 使用

1. 下载安装

2. Git的配置

   * 设置用户户名和邮箱

   ```sh
   git config --global user.name "yourname"
   git config --global user.email "your_email@youremail.com"
   ```

   * 生成密钥

   ```sh
   ssh-keygen -t rsa
   ```

   * 在github中添加密钥

   添加密钥：将上一步骤生成的密钥即.ssh/id_rsa.pub中内容全部复制。在github的 Settings-->SSH and GPG keys-->New SSH key，key中粘贴复制的内容(Title自定义)。

   验证：github输入第一条的命令，码云输入第二条

   ```sh
   ssh -T git@github.com
   ssh -T git@gitee.com
   ```

3. 创建远程仓库

   注意同名

4. 创建本地目录

   注意同名

5. ```sh
   git init
   ```

6. 链接远程仓库

```sh
git remote add origin https://github.com/yourName/repositoryname.git
```

7. 因为github现在默认的分支是mainfen分支本地默认分支是master

   先在本地新建一个main分支将将master分支合并到main上

   ```sh
   git checkout -b main
   # Switched to a new branch 'main'
   git branch
   # * main
   #  master
   git merge master # 将master分支合并到main上
   # Already up to date.
   git pull origin main --allow-unrelated-histories # git pull origin main会报错：refusing to merge unrelated histories
   git push origin main
   ```

8. 

```sh
git status　　　　　　　　　　查看工作目录的状态

git add <file>　　　　　　　　将文件添加到暂存区

git commit -m "commnet" 　　提交更改,添加备注信息(此时将暂存区的信息提交到本地仓库)

git push origin master 　　 将本地仓库的文件push到远程仓库(若 push 不成功，可加 -f 进行强推操作)
```

9. 

```java
//最好的办法先换掉http版本
1.(推荐)直接换掉Git的http版本
git config --global http.version HTTP/1.1
// 报错10053
git config --global http.postBuffer 524288000
// 443
git config --global --unset http.proxy //取消http代理
```

