package com.bpa;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class CreateBullet extends Sprite implements Disposable{
	public World world; // world player will live in
	public Body b2body; //creates body for player
	private BodyDef bdef = new BodyDef();
	public String id = "BULLET";
	public static Array<CreateBullet> bullets;
	Sprite sprite;
	TextureAtlas textureAtlas;
	TextureRegion textureRegion;
	public static float angle;
	private float speed = 10;
	private float posX;
	private float posY;
	public static ArrayList gruntList;
	
	public CreateBullet(World world) {
		this.world = world;

		defineBullet();
		textureAtlas = DunGun.manager.get("sprites/bullet.atlas", TextureAtlas.class);
		textureRegion = textureAtlas.findRegion("bullet");
		sprite =new Sprite(DunGun.manager.get("sprites/bullet.png", Texture.class));
		sprite.setOrigin((sprite.getWidth() / 2) / DunGun.PPM, (float) ((sprite.getHeight() / 2) / DunGun.PPM - .08));
		sprite.setSize(16 / DunGun.PPM, 16 / DunGun.PPM);
		sprite.setRotation(PlayerOne.angle); //!!!!!!!!!!
	}
	
	public void defineBullet() {
		bullets = new Array<CreateBullet>();
		bdef.position.set(PlayerOne.p1PosX, PlayerOne.p1PosY);
	
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		b2body.isBullet();
		FixtureDef fdef = new FixtureDef();
		//polygon shape for the laser
		if (GunSelectionScreen.weaponSelected == "laser") {
			PolygonShape shape = new PolygonShape();
			Vector2[] vertice = new Vector2[4];
			vertice[0] = new Vector2(3, 50).scl(1/DunGun.PPM);
			vertice[1] = new Vector2(8, 50).scl(1/DunGun.PPM);
			vertice[2] = new Vector2(3, 10).scl(1/DunGun.PPM);
			vertice[3] = new Vector2(8, 10).scl(1/DunGun.PPM);
			shape.set(vertice);
			fdef.shape = shape;
			//shape.dispose();
		}
		else {
			CircleShape shape = new CircleShape();			

			fdef.shape = shape;
			
			if (GunSelectionScreen.weaponSelected == "shotgun") {
				shape.setRadius(2 / DunGun.PPM);
				}
			else {
				shape.setPosition(new Vector2(5, 7).scl(1/DunGun.PPM));

				shape.setRadius(4 / DunGun.PPM);
					}
			//shape.dispose();
		}
		//Sets size of the physics bodies depending on the type of gun
		fdef.filter.categoryBits = DunGun.BULLET; //identifies the category bit is
		fdef.filter.maskBits = DunGun.WALL | DunGun.GRUNT; // what masking bit the category bit collides with
		b2body.createFixture(fdef).setUserData("bullets");
		//b2body.setTransform(b2body.getPosition().x, b2body.getPosition().y, PlayerOne.angle2); //sets the position of the body to the position of the body and implements rotation
		//fdef.shape.dispose();
		float differenceX = Level1.mousePosition.x - b2body.getPosition().x;
		float differenceY = Level1.mousePosition.y - b2body.getPosition().y;
		angle = MathUtils.atan2(differenceY, differenceX);
		//float angle = MathUtils.atan2(Level1.mouse_position.y - b2body.getPosition().y, Level1.mouse_position.x - b2body.getPosition().x) * MathUtils.radDeg; //find the distance between mouse and player

		//float posX = (float) (Math.cos(90)) ;
		if (GunSelectionScreen.weaponSelected == "shotgun") {
			float speedVary = (int)(Math.random() * 10 + 5);
			//float speedVary = .5f;
			float angleVary = (int)(Math.random() * 40 - 19);
			angleVary = angleVary / 100;
			angle = angle + angleVary;
			
			posX = (float) (Math.cos(angle)) * speedVary;
			posY = (float) (Math.sin(angle)) * speedVary;
		}
		
		else {
			if (GunSelectionScreen.weaponSelected == "laser"){
				speed = 2f;
			}
			posX = (float) (Math.cos(angle)) * speed;
			posY = (float) (Math.sin(angle)) * speed;
		    angle = angle - 1.6f ;
			b2body.setTransform(b2body.getPosition().x, b2body.getPosition().y, angle); //sets the position of the body to the position of the body and implements rotation

		}
		

		
		b2body.applyLinearImpulse(posX, posY, b2body.getWorldCenter().x, b2body.getWorldCenter().y, true);
		
	
	}
	
	public void renderSprite(SpriteBatch batch) {

		float posX = b2body.getPosition().x - .05f;
		float posY = b2body.getPosition().y;
		//System.out.println(getX() + " " + getY());
		sprite.setPosition(posX, posY);


		sprite.draw(batch);
		
		
	}
	
	@Override
	public void dispose() {
		textureAtlas.dispose();
	}
}
