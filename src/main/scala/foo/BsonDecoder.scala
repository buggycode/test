package foo
import org.jboss.netty.handler.codec.frame.FrameDecoder
import org.jboss.netty.channel.{Channel, ChannelHandlerContext}
import org.jboss.netty.buffer.ChannelBuffer

class BsonDecoder extends FrameDecoder {
  override def decode(ctx: ChannelHandlerContext, channel: Channel, buffer: ChannelBuffer): Object = {
    /*
    if (buffer.readableBytes()<4) { // first 4byte : pkt size
      
    }
    
    null
    */
    println("BsonDecoder.decode")
    buffer.readBytes(buffer.readableBytes) // streamming no decode now
  }
}