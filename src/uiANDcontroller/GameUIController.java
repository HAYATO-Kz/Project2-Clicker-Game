package uiANDcontroller;

import java.util.Observable;
import java.util.Observer;

import Alert.AlertBox;
import Register.Database;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import monsterANDmoney.Money;
import monsterANDmoney.Monster;
import sound.MusicBox;
import sound.Sound;
import unitANDhero.Hero;
import unitANDhero.autoAttack;

/**
 * Controller for GameUI
 * 
 * @author Hayato Kawai
 *
 */
public class GameUIController implements Observer {

	@FXML
	Label purse;
	@FXML
	Label monsterHPlabel, monsterNamelabel;
	@FXML
	Label level, round;
	@FXML
	Label weaponUseDamage, weaponUseName, weaponUseCost;
	@FXML
	Label weaponNextDamage, weaponNextName, weaponNextCost;
	@FXML
	Label status, status2, status3, status4;
	@FXML
	Label unit1Cost, unit2Cost, unit3Cost, unit4Cost;
	@FXML
	ImageView monsterp;
	@FXML
	ImageView weaponUse, weaponNext;
	@FXML
	ComboBox<MusicBox> musicbox;

	private Database data = Database.getInstance();
    private String monName;
	private Money money;
	private Monster monster;
	private Hero hero;
	private Sound sound;
	private Thread autoattack;
	private autoAttack at;
	private int spawn = data.team;
	private int unit1 = data.unit1;
	private int unit2 = data.unit2;
	private int unit3 = data.unit3;
	private int unit4 = data.unit4;
	
	/**
	 * set game's beginning
	 */
	public void set() {
		if (musicbox != null) {
			musicbox.getItems().addAll(MusicBox.values());
			musicbox.getSelectionModel().select(0); // select an item to show
		}
		String name = musicbox.getValue().getName();
		String type = musicbox.getValue().getTypeM();
		sound.stopB();
		sound.playBack(name, 0.5, type);
		monster.newMonster(data.level);
		monName = monster.getName();
		at = new autoAttack(money, monster);
		autoattack = new Thread(at);
		autoattack.setDaemon(true);
		if(unit1 == 1) {
			status.setText("UPGRADE");
			at.member(1,1);
		}
		if(unit2 == 1) {
			status2.setText("UPGRADE");
			at.member(2,1);
		}
		if(unit3 == 1) {
			status3.setText("UPGRADE");
			at.member(3,1);
		}
		if(unit4 == 1) {
			status4.setText("UPGRADE");
			at.member(4,1);
		}
		if(spawn == 1) runThread();
		hero.setWeapon(0);
		monsterp.setImage(monster.getMonsterImage());
		display();

	}

	/**
	 * set class value.
	 */
	public void addClass(Money money, Monster monster, Hero hero, Sound sound) {
		this.money = money;
		this.monster = monster;
		this.hero = hero;
		this.sound = sound;
		set();
	}
	
	/**
	 * call buyNewWeapon method from Hero class
	 */
	public void setNewWeapon(ActionEvent ae) {
		click();
		hero.buyNewWeapon();
	}

	/**
	 * call upgradeWeapon method from Hero class
	 */
	public void upgradeWeapon(ActionEvent ae) {
		click();
		hero.upgradeWeapon();
	}

	/**
	 * attack monster, play soundFX and set monster reaction.
	 * @param m
	 */
	public void attack(MouseEvent m) {
		monster.attack(hero.getHeroattack());
		sound.playEffect("sword", 0.8);
		monsterp.setImage(monster.getMonsterzImage());
	}
	/**
	 * set monster image to normal.
	 */
	public void attacked(MouseEvent m) {
		monsterp.setImage(monster.getMonsterImage());
	}

	/**
	 * check auto-attack thread and unit1's status.if didn't run, start running.
	 */
	public void Unit1(ActionEvent ae) {
		click();
		if (spawn == 0) {
			runThread();
		}
		if(at.member(1,0)) {
			status.setText("UPGRADE");
			unit1=1;
		}
	}

	/**
	 * check auto-attack thread and unit2's status.if didn't run, start running.
	 */
	public void Unit2(ActionEvent ae) {
		click();
		if (spawn == 0) {
			runThread();
		}
		if(at.member(2,0)) {
			status2.setText("UPGRADE");
			unit2=1;
		}
	}

	/**
	 * check auto-attack thread and unit3's status.if didn't run, start running.
	 */
	public void Unit3(ActionEvent ae) {
		click();
		if (spawn == 0) {
			runThread();
		}
		if(at.member(3,0)) {
			status3.setText("UPGRADE");
			unit3=1;
		}
	}

	/**
	 * check auto-attack thread and unit4's status.if didn't run, start running.
	 */
	public void Unit4(ActionEvent ae) {
		click();
		if (spawn == 0) {
			runThread();
		}
		if(at.member(4,0)) {
			status4.setText("UPGRADE");
			unit4=1;
		}
	}
	
	/**
	 * change cursor's image when cursor enter monsterp zone
	 */
	public void enterArea(MouseEvent me) {
		Scene scene = monsterp.getScene();
		String cursor = "/picture/" + hero.getUseWeaponName() + ".png";
		Image image = new Image(cursor);
		scene.setCursor(new ImageCursor(image));
	}
	
	/**
	 * change cursor's image when cursor exit monsterp zone
	 */
	public void exitArea(MouseEvent me) {
		Scene scene = monsterp.getScene();
		String cursor = "/picture/Cursor.png";
		Image image = new Image(cursor);
		scene.setCursor(new ImageCursor(image));
	}
	
	/**
	 * change background music and background image by musicbox's value.
	 */
	public void changeMusic(ActionEvent ae) {
		Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
		String name = musicbox.getValue().getName();
		String typeM = musicbox.getValue().getTypeM();
		sound.stopB();
		sound.playBack(name, 0.5, typeM);
	}

	/**
	 * update data
	 */
	public void close () {
		data.update(data.id, monster.getLevel(), monster.getRound(), money.getMoney(), spawn, unit1, unit2, unit3, unit4, hero.getHeroattack(), hero.getWeaponSlot());
	}
	
	/**
	 * run auto-attack thread
	 */
	public void runThread() {
		try {
			spawn = 1;
			autoattack.join();
			autoattack.start();
		} catch (InterruptedException e) {
		}
	}
	
	public void click() {
		sound.playEffect("click", 1);
	}

	/**
	 * update display of GameUI.
	 */
	public void display() {
		Platform.runLater(() -> {
			try {
				if (monName != monster.getName())monsterp.setImage(monster.getMonsterImage());
				monName = monster.getName();
				level.setText(Integer.toString(monster.getLevel()));
				round.setText(Integer.toString(monster.getRound()));
				weaponUse.setImage(hero.getWeaponImage("use"));
				weaponUseDamage.setText(Integer.toString(hero.getUseWeaponDamage()));
				weaponUseName.setText(hero.getUseWeaponName());
				weaponUseCost.setText(Integer.toString(hero.getUseWeaponDamage()/5));
				weaponNext.setImage(hero.getWeaponImage("next"));
				weaponNextDamage.setText(Integer.toString(hero.getNextWeaponDamage()));
				weaponNextName.setText(hero.getNextWeaponName());
				weaponNextCost.setText(Integer.toString(hero.getNextWeaponDamage()));
				purse.setText(Integer.toString(money.getMoney()));
				monsterHPlabel.setText(Integer.toString(monster.MonsterHp()));
				monsterNamelabel.setText(monster.getName());
				if(unit1 ==0)unit1Cost.setText(Integer.toString(at.memberSpawnCost(1)));
				else unit1Cost.setText(Integer.toString(at.memberUpgradeCost(1)));
				if(unit2 == 0)unit2Cost.setText(Integer.toString(at.memberSpawnCost(2)));
				else unit2Cost.setText(Integer.toString(at.memberUpgradeCost(2)));
				if(unit3 == 0)unit3Cost.setText(Integer.toString(at.memberSpawnCost(3)));
				else unit3Cost.setText(Integer.toString(at.memberUpgradeCost(3)));
				if(unit4 == 0)unit4Cost.setText(Integer.toString(at.memberSpawnCost(4)));
				else unit4Cost.setText(Integer.toString(at.memberUpgradeCost(4)));
			} catch (Exception e) {}
		});
	}

	/**
	 *  update display
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		display();
	}


}
