package foo

import org.jboss.netty.channel.{ ChannelEvent, ChannelHandlerContext, SimpleChannelUpstreamHandler, MessageEvent, ExceptionEvent, ChannelStateEvent }
import java.util.concurrent.ConcurrentHashMap

class FrontServerHandler extends SimpleChannelUpstreamHandler {
  var clientId:Int = 0
  var clients = new ConcurrentHashMap[ChannelHandlerContext,Int]()
  
  override def handleUpstream(ctx: ChannelHandlerContext, e: ChannelEvent) {
    println("FrontServerHandler.handlerUpstream "+ this)
    super.handleUpstream(ctx, e)
  }

  override def messageReceived(ctx: ChannelHandlerContext, e: MessageEvent) {
    println("FrontServerHandler.messageReceived "+ this)
    //e.getChannel().write(e.getMessage())
    var msg = e.getMessage()
    
    var ks =  this.clients.keys()
    while (ks.hasMoreElements()) {
      ks.nextElement().getChannel().write(msg)
    }
  }

  override def channelDisconnected(ctx: ChannelHandlerContext, e: ChannelStateEvent) {
    println("FrontServerHandler.channelDisconnected " + this )
    clients.remove(ctx)
    println("clients = " + clients)
  }

  override def channelConnected(ctx: ChannelHandlerContext, e: ChannelStateEvent) {
    println("FrontServerHandler.channelConnected " + this )
    clients.put(ctx, this.clientId)
    clientId += 1
    println("clients = " + clients)
  }
  
  override def exceptionCaught(ctx: ChannelHandlerContext, e: ExceptionEvent) {
    println("FrontServerHandler.exceptionCaught "+ this)
    clients.remove(ctx)
    e.getChannel.close

  }
}