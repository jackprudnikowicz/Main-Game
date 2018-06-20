package gdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import javafx.scene.layout.Background;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	boolean Gameon, Eggdrop, Duckdead;
	Texture txtBg, txtGuy, txtRng, txtDuck, txtEgg;
	Sprite sprGuy, sprDuck, sprEgg;
	int nScreen=1, nHealth=3;
	float fEggY;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Gameon=false;
		Eggdrop = false;
		Duckdead = false;
		txtBg = new Texture("Background.jpg");
		txtGuy = new Texture("0.png");
		txtRng = new Texture("Range.png");
		txtDuck = new Texture("Duck.png");
		txtEgg = new Texture("Egg.png");
		sprGuy = new Sprite(txtGuy);
		sprGuy.setPosition(0, 200);
		sprDuck = new Sprite(txtDuck);
		sprDuck.setPosition(txtBg.getWidth(), 650);
		sprEgg = new Sprite(txtEgg);
		sprEgg.setPosition(sprDuck.getX(), sprDuck.getY());
		fEggY = sprDuck.getY();
	}

	@Override
	public void render () {
		//System.out.println(dGrav);
		System.out.println(sprDuck.getX());
		if(Gameon==false){
		if (nScreen == 3) {
			if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
				nScreen = 1;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			sprGuy.translateX(-5f);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			sprGuy.translateX(5f);
		}
		if (sprGuy.getX() >= txtBg.getWidth() - sprGuy.getWidth() / 2) {
			nScreen = 2;
			sprGuy.setPosition(0, 200);
		}
		if (sprGuy.getX() <= -1 && nScreen == 2) {
			nScreen = 1;
			sprGuy.setPosition(txtBg.getWidth() - sprGuy.getWidth() / 2, 200);
		} else if (sprGuy.getX() <= -1) {
			nScreen = 3;
		}
	}
		if(nScreen==2){
			if(sprGuy.getX()==300){
				if(nHealth==3) {
					Gameon = true;
				}
			}
		}
		//Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if(nScreen ==1){
			batch.draw(txtBg, 0, -95);
		}
		else if(nScreen ==2){
			batch.draw(txtRng, 0, -35, 1000, 800);
		}
		if(Gameon==true) {
			batch.draw(sprDuck, sprDuck.getX(), sprDuck.getY(), 100, 80);
			if(Duckdead==false) {
				sprDuck.translateX(-2f);
			}
			if (sprDuck.getX() < 0 - sprDuck.getWidth()) {
				sprDuck.setPosition(txtBg.getWidth(), 500);
			}
			if (sprDuck.getX() == sprGuy.getX()) {
				Eggdrop = true;
			}
			if (nHealth == 0) {
				Gameon = false;
			}
			if (Eggdrop == true) {
				batch.draw(sprEgg, 300, fEggY, 50, 50);
				fEggY -= 3;
				//sprEgg.translateY(-7f);
			}
			if (fEggY <= sprGuy.getY() + sprGuy.getHeight()) {
				nHealth -= 1;
				fEggY = sprDuck.getY();
				Eggdrop = false;
			}
		}
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			System.out.println("asdfasdfasdf");
			if(sprDuck.getX()>sprGuy.getX()) {
				if (Gdx.input.getX() >= sprDuck.getX() && Gdx.input.getX() <= sprDuck.getX() + sprDuck.getWidth()) {
					Duckdead = true;
				}
			}
		}
		if(Duckdead == true){
			sprDuck.rotate(180);
			batch.draw(sprDuck, sprDuck.getX(), sprDuck.getY(), 100, 80);
			sprDuck.translateY(-3f);
			if (sprDuck.getY()<200){
				sprDuck.setPosition(sprDuck.getX(), 200);
			}
		}
		batch.draw(txtGuy, sprGuy.getX(), sprGuy.getY());
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		txtBg.dispose();
		txtGuy.dispose();
		txtRng.dispose();
		txtDuck.dispose();
	}
}