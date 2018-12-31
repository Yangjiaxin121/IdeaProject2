package com.bluegrass.python;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @version V0.1.0
 * @Description: java thrift 客户端
 * @see
 * @since
 */
public class ThriftClient {
    public static String startClient(String word) {
        TTransport transport;
        try {
            System.out.println("thrift client connext server at 8989 port ");
            transport = new TSocket("127.0.0.1", 8989);
            TProtocol protocol = new TBinaryProtocol(transport);
            Hello.Client client = new Hello.Client(protocol);
            transport.open();
            System.out.println(client.helloString(word));
            String string = client.helloString(word);
            transport.close();
            System.out.println("thrift client close connextion");
            return string;
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("thrift client init ");
        ThriftClient client = new ThriftClient();
        System.out.println("thrift client start ");

        System.out.println(client.startClient("wobukaixin"));
        System.out.println("thrift client end ");
    }
}