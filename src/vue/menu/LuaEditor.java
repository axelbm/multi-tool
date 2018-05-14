package vue.menu;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Language.Dictionary;
import vue.Menu;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import com.sun.javafx.webkit.WebConsoleListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import javafx.concurrent.Worker.State;
import netscape.javascript.JSObject;


@SuppressWarnings("restriction")
public class LuaEditor extends Menu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	
	public JavaApplication bridge;
	
	public String code = "";
	
	
	
	public LuaEditor() {
		
		
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{0, 185, 76, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{114, 97, 0};
		gbl_panel_1.rowHeights = new int[]{25, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton btnNewButton = new JButton("Run");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Globals globals = JsePlatform.standardGlobals();
				LuaValue chunk = globals.load(bridge.luaCode);
				chunk.call();

			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel_1.add(btnNewButton, gbc_btnNewButton);
		//add(panel, gbc_panel);
		
		
		
		JFXPanel jfxPanel = new JFXPanel();
		GridBagConstraints gbc_jfxPanel = new GridBagConstraints();
		gbc_jfxPanel.fill = GridBagConstraints.BOTH;
		gbc_jfxPanel.insets = new Insets(0, 0, 5, 0);
		gbc_jfxPanel.gridx = 0;
		gbc_jfxPanel.gridy = 1;
		add(jfxPanel, gbc_jfxPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		JEditorPane editorPane = new JEditorPane();
		scrollPane.setViewportView(editorPane);

		// Creation of scene and future interactions with JFXPanel
		// should take place on the JavaFX Application Thread
		
		WebConsoleListener.setDefaultListener(new WebConsoleListener(){
		    @Override
		    public void messageAdded(WebView webView, String message, int lineNumber, String sourceId) {
		        System.out.println("Console: [" + sourceId + ":" + lineNumber + "] " + message);
		    }
		});
		
		Platform.runLater(() -> {
		    WebView webView = new WebView();
		    WebEngine webEngine = webView.getEngine();
		    
		    jfxPanel.setScene(new Scene(webView));
		    
		    File f = new File("resources/ace.html");
		    webEngine.load(f.toURI().toString());
		    
		    bridge = new JavaApplication();
		    webEngine.getLoadWorker().stateProperty().addListener((observableValue, oldValue, newValue) -> {
		        if (newValue == State.SUCCEEDED) {
		            JSObject window = (JSObject) webEngine.executeScript("window");
		            window.setMember("app", bridge);
		        }
		    });
		    
		    webEngine.setOnAlert(event -> {
		        System.out.println(" alerted: " + event.getData());
		    });
		});

		
	}

	public void setLanguage(Dictionary dict) {
		
	}
	
	
	public class JavaApplication {
		
		public String luaCode = new String();
		
		
		public void updateDocument(String code) {
			luaCode = code;
		}
		
	}
	
	public class LuaApplication {
		
	}

}
