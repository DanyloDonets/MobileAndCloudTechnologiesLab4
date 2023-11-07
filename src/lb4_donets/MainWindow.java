package lb4_donets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainWindow {
	private Text wordText;
	private Text symbolText;
	private static int numOfSymbol;
	private static String word;
	private static String laters;
	private static int numOfLaters;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Label wordLbl = new Label(shell, SWT.NONE);
		wordLbl.setBounds(28, 10, 69, 15);
		wordLbl.setText("Write word");
		
		wordText = new Text(shell, SWT.BORDER);
		wordText.setBounds(103, 4, 170, 21);
		
		
		Label lblSymbolNumber = new Label(shell, SWT.NONE);
		lblSymbolNumber.setBounds(10, 42, 87, 15);
		lblSymbolNumber.setText("Symbol number");
		
		symbolText = new Text(shell, SWT.BORDER);
		symbolText.setBounds(103, 42, 76, 21);
		
		Label symbolLbl = new Label(shell, SWT.NONE);
		symbolLbl.setBounds(10, 120, 55, 15);
		symbolLbl.setText("Symbol: ");
		
		Label latersLbn = new Label(shell, SWT.NONE);
		latersLbn.setBounds(133, 120, 75, 131);
		latersLbn.setText("Laters: ");
		
		Label numOfLatersLbn = new Label(shell, SWT.NONE);
		numOfLatersLbn.setBounds(229, 120, 139, 15);
		numOfLatersLbn.setText("Num of laters: ");
		
		Button countBtn = new Button(shell, SWT.NONE);
		countBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					numOfSymbol = Integer.parseInt(symbolText.getText());
					word = wordText.getText();
					laters = "";
					numOfLaters = 0;
					if (word.contains(" ")) {
						MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
						box.setText("Error");
						box.setMessage("This is sentence? not word!");
						box.open();
					}
					else {
					if(word.length()<numOfSymbol) {
						MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
						box.setText("Error");
						box.setMessage("Write correct number");
						box.open();
					}
					
					for (int i = 0; i < word.length(); i++) {
			            Boolean found = false;
			            for (int j = 0; j < laters.length(); j++) {
			                if (word.charAt(i) == laters.charAt(j)) {
			                    found = true;
			                    break; //don't need to iterate further
			                }
			            }
			            if (found == false) {
			            	laters = laters.concat(String.valueOf(word.charAt(i))) + "\n";
			            	numOfLaters++;
			            }
			        }
					symbolLbl.setText("Symbol: "+word.charAt(numOfSymbol+1));
					
					latersLbn.setText("laters : \n" + laters);
					numOfLatersLbn.setText("Num of laters: " + numOfLaters);
					}
				}catch(Exception e1) {
				
					MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
					box.setText("Error");
					box.setMessage("Write correct number");
					box.open();
				
				}
				
			}
		});
		countBtn.setBounds(133, 81, 75, 25);
		countBtn.setText("Count");
		
		
		
		

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}
