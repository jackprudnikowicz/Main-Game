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
	boolean Gameon;
	Texture txtBg, txtGuy, txtRng, txtDuck, txtEgg;
	Sprite sprGuy, sprDuck, sprEgg;
	int nScreen=1, nHealth=3;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Gameon=false;
		txtBg = new Texture("Background.jpg");
		txtGuy = new Texture("0.png");
		txtRng = new Texture("Range.jpg");
		txtDuck = new Texture("Duck.png");
		txtEgg = new Texture("Egg.png");
		sprGuy = new Sprite(txtGuy);
		sprGuy.setPosition(0, 200);
		sprDuck = new Sprite(txtDuck);
		sprDuck.setPosition(txtBg.getWidth(), 650);
		sprEgg = new Sprite(txtEgg);
		sprEgg.setPosition(sprDuck.getX(), sprDuck.getY());
	}

	@Override
	public void render () {
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
			batch.draw(txtRng, 0, 0, 1000, 800);
		}
		if(Gameon==true){
			batch.draw(sprDuck, sprDuck.getX(), sprDuck.getY(), 100, 80);
			sprDuck.translateX(-2f);
			if(sprDuck.getX()<0-sprDuck.getWidth()){
				sprDuck.setPosition(txtBg.getWidth(), 500);
			}
			if(sprDuck.getX()==sprGuy.getX()){
				batch.draw(sprEgg, sprDuck.getX(), sprDuck.getY(), 50, 50);
				sprEgg.translateY(-7f);
				nHealth-=1;
			}
			if(nHealth == 0){
				Gameon = false;
			}
		}
		//Make egg drop if statment, that starts when their x,s are equal
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
