package foo

import org.jboss.netty.channel.{ ChannelEvent, ChannelHandlerContext, SimpleChannelUpstreamHandler, MessageEvent, ExceptionEvent, ChannelStateEvent }

class FrontServerHandler extends SimpleChannelUpstreamHandler {
  var clientId:Int = 0
  var clients:Map[ChannelHandlerContext,Int] = Map.empty
  
  override def handleUpstream(ctx: ChannelHandlerContext, e: ChannelEvent) {
    println("FrontServerHandler.handlerUpstream "+ this)
    super.handleUpstream(ctx, e)
  }

  override def messageReceived(ctx: ChannelHandlerContext, e: MessageEvent) {
    println("FrontServerHandler.messageReceived "+ this)
    //e.getChannel().write(e.getMessage())
    var msg = e.getMessage()
    for( (ctx,id) <- this.clients ) {
      ctx.getChannel().write(msg)
    }
  }

  override def channelDisconnected(ctx: ChannelHandlerContext, e: ChannelStateEvent) {
    println("FrontServerHandler.channelDisconnected " + this )
    clients -= ctx
    println("clients = " + clients)
  }

  override def channelConnected(ctx: ChannelHandlerContext, e: ChannelStateEvent) {
    println("FrontServerHandler.channelConnected " + this )
    clients += ( ctx -> this.clientId )
    clientId += 1
    println("clients = " + clients)
  }
  
  override def exceptionCaught(context: ChannelHandlerContext, e: ExceptionEvent) {
    println("FrontServerHandler.exceptionCaught "+ this)
    e.getChannel.close
  }
}