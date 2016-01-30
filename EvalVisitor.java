import org.antlr.v4.runtime.tree.ParseTree;
import java.io.*;

public class EvalVisitor extends sysBaseVisitor
{
		class LogEntry {
		String ident1;
		String dayNum;
		String time;
		String ip;
		String ipfull;
		String user;
		String proto;
		String ipfull1;
		String ipfull2;
		String mac;
		//String x;

	  }
	  

	  static LogEntry logEntry;

	  @Override
	  public Object visit(ParseTree tree) {
		/* Setup logentry used by all visitors (this case, there is only a single visitor...)*/
		logEntry = new LogEntry();

		/* visit */
		final Object o = super.visit(tree);
		
		/* do something with the results */
		return logEntry.ident1+" "+logEntry.dayNum+" "+logEntry.time+","+logEntry.ip+","+logEntry.user+","+logEntry.mac+","+logEntry.proto+","+logEntry.ipfull+","+logEntry.ipfull1 ;//+","+logEntry.ipfull2;
	  }

	  StringBuilder stringBuilder;
	  
	  @Override
	  public Object visitR(sysParser.RContext ctx) {
		logEntry.ident1 = ctx.IDENT().getText();
		logEntry.dayNum = ctx.NUM().getText();
		return super.visitR(ctx);
		}
	  @Override
	  public Object visitTime(sysParser.TimeContext ctx) {
		logEntry.time = ctx.getText();
		return super.visitTime(ctx);
	  }
	  
	  @Override
	  public Object visitMac(sysParser.MacContext ctx) {
		logEntry.mac = ctx.getText();
		return super.visitMac(ctx);
	  }
	  
	  @Override
	  public Object visitXx(sysParser.XxContext ctx) {
		return super.visitXx(ctx);
	  }

	  @Override
	  public Object visitIp(sysParser.IpContext ctx) {
		logEntry.ip = ctx.getText();
		return super.visitIp(ctx);
	  }

	  @Override
	  public Object visitIpfull(sysParser.IpfullContext ctx) {
		logEntry.ipfull = ctx.getText();
		return super.visitIpfull(ctx);
	  }
	  
	  @Override
	  public Object visitIpfull1(sysParser.Ipfull1Context ctx) {
		logEntry.ipfull1 = ctx.getText();
		return super.visitIpfull1(ctx);
	  }
	  
	  
	  
	  @Override
	  public Object visitXipfull(sysParser.XipfullContext ctx) {
		return super.visitXipfull(ctx);
	  }

	  @Override
	  public Object visitX(sysParser.XContext ctx) {
		return super.visitX(ctx);
	  }

	  @Override
	  public Object visitUser(sysParser.UserContext ctx) {
		logEntry.user = ctx.getText();
		return super.visitUser(ctx);
	  }

	  @Override
	  public Object visitXuser(sysParser.XuserContext ctx) {
		return super.visitXuser(ctx);
	  }

	  @Override
	  public Object visitXout(sysParser.XoutContext ctx) {
		return super.visitXout(ctx);
	  }

	  @Override
	  public Object visitProto(sysParser.ProtoContext ctx) {
		logEntry.proto = ctx.getText();
		return super.visitProto(ctx);
	  }

	  @Override
	  public Object visitXtra(sysParser.XtraContext ctx) {
		return super.visitXtra(ctx);
	  }

	  @Override
	  public Object visitXtra1(sysParser.Xtra1Context ctx) {
		return super.visitXtra1(ctx);
	  }

	  @Override
	  public Object visitXtra2(sysParser.Xtra2Context ctx) {
		return super.visitXtra2(ctx);
	  }

	  @Override
	  public Object visitXtra3(sysParser.Xtra3Context ctx) {
		return super.visitXtra3(ctx);
	  }
	  
	  
 }