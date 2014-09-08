package newpackage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tomás Abril
 */
public class ExecCmd extends Thread {

	String cmd;
	Process proc;
	int index;

	public ExecCmd(String cmd) {
		this.cmd = cmd;
	}

	@Override
	public void run() {
		Runtime rt = Runtime.getRuntime();
		//System.out.println("Executando " + cmd);
		try {
			proc = rt.exec(cmd);
			proc.waitFor();
			//Janela.updateTable();
		} catch (IOException | InterruptedException ex) {
			//System.out.println("erro na execucao");
			Logger.getLogger(ExecCmd.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public synchronized void cancela() {
		if (proc != null) {
			proc.destroy();
		}
	}

	public synchronized Boolean terminado() {
		//if (proc != null) {
			return !proc.isAlive();
		//}
		//return true;
	}

	public String getCmd() {
		return cmd;
	}

	public int getIndex() {
		return index;
	}

	public synchronized void setIndex(int index) {
		this.index = index;
	}

}
