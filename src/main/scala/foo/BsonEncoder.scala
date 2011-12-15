package foo

import org.jboss.netty.buffer.ChannelBuffers
import org.jboss.netty.channel.{ Channel, ChannelHandlerContext }
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder

class BsonEncoder extends OneToOneEncoder {
  override def encode(ctx: ChannelHandlerContext, channel: Channel, msg: Object): Object = {
    println("BsonEncoder.encode")
    msg
  }
}