## 七层网络模型

![](https://image-static.segmentfault.com/119/190/119190082-5aba3da90a5aa_articlex)

## TCP和UDP连接

### TCP的三次握手和四次分手
<img src="https://image-static.segmentfault.com/343/619/3436192742-56fde907b6f8f" style="zoom:50%;" />

**第一次握手**：建立连接。客户端发送连接请求报文段，将SYN位置为1， Sequence Number为x；然后客户端进入`SYN_SEND`状态，等待服务器的确认；

**第二次握手**：服务器收到客户端的SYN报文段，需要对这个SYN报文段进行确认，设置Acknowledgment Number为x+1；同时，自己还要发送SYN请求信息，将SYN位置为1，Sequence Number为y；服务器端将上述所有信息放到一个报文段(即SYN+ACK报文段)，并一并发送给客户端，此时服务器进去`SYN_RECV`状态

**第三次握手**：客户端收到服务器的`SYN+ACK`报文段。然后将Acknowledgment Number设置为y+1，向服务器发送ACK报文段，这个报文段发送完毕以后，客户端和服务端都进入`ESTABLISHED`状态，完成TCP三次握手。

**第一次分手**：主机1(可以是客户端，也可以是服务端)，设置Seqence Number和Acknowledgment Number，向主机2发送一个FIN报文段；此时，主机1进入`FIN_WAIT_1`状态；这表示主机1没有数据要发送给主机2了；

**第二次分手**：主机2收到了主机1发送的FIN报文段，向主机1回一个ACK报文段，Acknowledgment Number为Sequence Number加1；主机1进入`FIN_WAIT_2`状态；主机2告诉主机1，我“同意”你的关闭请求；

**第三次分手**：主机2向主机1发送FIN报文段，请求关闭连接，同时主机2进入`LAST_ACK`状态；

**第四次分手**：主机1收到主机2发送的FIN报文段，向主机2发送ACK报文段，然后主机1进入`Time_WAIT`状态；主机2收到主机1的ACK报文段后，就关闭连接；此时，主机1等待2MSL后依然没有收到回复，则证明Server已正常关闭，那好，主机1也可以关闭连接了。