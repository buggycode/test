package foo
import org.jboss.netty.bootstrap.ServerBootstrap
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory
import java.util.concurrent.Executors
import java.net.InetSocketAddress

class Server {
  def start {
    println("start server..")

    var bootstrap = new ServerBootstrap(
      new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()))

    bootstrap.setPipelineFactory(new FrontServerPipelineFactory())
    bootstrap.bind(new InetSocketAddress(8080))
  }

}