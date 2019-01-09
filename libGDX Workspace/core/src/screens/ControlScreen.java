package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import BackEnd.Mutagen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class ControlScreen implements Screen, InputProcessor{
	final Mutagen game;
	Texture controlScreen;
	private OrthographicCamera cam;
	private Viewport gamePort;
	boolean justClicked = false;
	boolean onCrash = true;
	private Vector3 mouse_position = new Vector3(0, 0, 0);
	private boolean buttonPressed = false;
	private float mX, mY;
	BitmapFont inactiveMenuText;
	BitmapFont activeMenuText;

	
	public ControlScreen(final Mutagen game) {
		this.game = game;
		controlScreen = new Texture("screens/tutorials/controlScreen.jpg");
		cam = new OrthographicCamera();		
		gamePort = new FitViewport(Mutagen.V_WIDTH / Mutagen.PPM, Mutagen.V_HEIGHT / Mutagen.PPM, cam); //fits view port to match map's dimensions (in this case 320x320) and scales. Adds black bars to adjust
		cam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0); //centers the map to center of screen
		Gdx.input.setInputProcessor(this);

		inactiveMenuText = Mutagen.manager.get("fonts/inactiveMenu(36).fnt", BitmapFont.class);
		activeMenuText = Mutagen.manager.get("fonts/activeMenu(36).fnt", BitmapFont.class);
	
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0 , 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		mouse_position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		cam.unproject(mouse_position);
		
		game.batch.begin();
		game.batch.setProjectionMatrix(cam.combined);
		
		game.batch.draw(controlScreen, 0 / Mutagen.PPM, 0 / Mutagen.PPM);
		
		
		mX = mouse_position.x;
		mY = mouse_position.y;
		System.out.println(mX + " " + mY);
		if (0 < mX && mX < 120 && 0 < mY && mY < 61)
		{
			activeMenuText.draw(game.batch, "BACK", 10 / Mutagen.PPM, 55);
		}else {
			inactiveMenuText.draw(game.batch, "BACK", 10, 55);
		}
		
	
		
		cam.update();
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);		
	}

	@Override
	public void pause() {
	
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		game.batch.dispose();
		controlScreen.dispose();
		}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {


		
		if (!buttonPressed) {
			//back button
			
			if (mX < 120 && mY < 61)  {
				
				Mutagen.clicking();
				game.setScreen(new Tutorial(game));
			}
		}
		buttonPressed = true;
		return false;

	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		buttonPressed = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
