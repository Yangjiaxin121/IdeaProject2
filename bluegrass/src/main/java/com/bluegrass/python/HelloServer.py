from com.bluegrass.python.example2.Tecent_interface_test import get_content
from com.bluegrass.python.hello import Hello
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer

class HelloHandler:
    def __init__(self):
     pass

    def helloString(self, word):
        polar,confd,text = get_content(word)
        string = str(polar)+','+str(confd)
        return string

#handler processer类
handler = HelloHandler()
processor = Hello.Processor(handler)
transport = TSocket.TServerSocket("127.0.0.1", 8989)
#传输方式，使用buffer
tfactory = TTransport.TBufferedTransportFactory()
#传输的数据类型：二进制
pfactory = TBinaryProtocol.TBinaryProtocolFactory()
#创建一个thrift 服务
server = TServer.TSimpleServer(processor, transport, tfactory, pfactory)

print("Starting thrift server in python...")
server.serve()
print("done!")
