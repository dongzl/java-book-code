package com.java.book.self.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * @author dongzonglei
 * @description
 * @date 2019-08-09 16:57
 */
public class TestActor {

    // 该 Actor 当收到消息 message 后，
    // 会打印 Hello message
    static class HelloActor extends UntypedActor {
        @Override
        public void onReceive(Object message) throws Exception {
            System.out.println("Hello " + message);
        }
    }

    public static void main(String args[]) throws Exception {
        // 创建 Actor 系统
        ActorSystem system = ActorSystem.create("HelloSystem");
        // 创建 HelloActor
        ActorRef helloActor = system.actorOf(Props.create(HelloActor.class));
        // 发送消息给 HelloActor
        helloActor.tell("Actor", ActorRef.noSender());
    }
}
