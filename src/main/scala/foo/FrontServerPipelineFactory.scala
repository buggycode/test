package foo
import org.jboss.netty.channel.{ ChannelPipeline, ChannelPipelineFactory }

class FrontServerPipelineFactory extends ChannelPipelineFactory {
  var handler = new FrontServerHandler
  override def getPipeline: ChannelPipeline = {
    println(this + " " + this.getClass())
    val pipeline = org.jboss.netty.channel.Channels.pipeline
    pipeline.addLast("decoder", new BsonDecoder)
    pipeline.addLast("encoder", new BsonEncoder)
    pipeline.addLast("handler", handler)
    pipeline
  }
}