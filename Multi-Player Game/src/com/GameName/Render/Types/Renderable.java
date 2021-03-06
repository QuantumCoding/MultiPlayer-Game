package com.GameName.Render.Types;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import com.GameName.Engine.GameEngine;
import com.GameName.Engine.ResourceManager.Threads;
import com.GameName.Render.Effects.Shader;
import com.GameName.Render.Effects.Texture;
import com.GameName.Util.Vectors.Vector2f;
import com.GameName.Util.Vectors.Vector3f;

public abstract class Renderable {
	protected final GameEngine ENGINE;
	
	private Shader shader;	
	private Texture texture;	
	private Vector3f color;	

	protected Vector2f texCoordsTop, texCoordsBottom;
	
	private boolean isVboUpdating;
	protected boolean vboUpdateNeeded;
	protected boolean needsVBOids;
	
	protected int vertexVBO;
	protected int textureVBO;
	protected int colorVBO;
	
	private Vector3f center;
	private Vector3f rotation;
	private Vector3f translation;
	private Vector3f scale;
	
	protected String name;
	
	public Renderable(GameEngine eng) {
		ENGINE = eng;
		
		vertexVBO = -1;
		textureVBO = -1;
		colorVBO = -1;
		
		needsVBOids = true;
		vboUpdateNeeded = true;
		
		center = new Vector3f(0, 0, 0);
		translation = new Vector3f(0, 0, 0);
		rotation = new Vector3f(0, 0, 0);
		scale = new Vector3f(1, 1, 1);
		
		name = "default";
	}
	
	public void render() {
		if(checkVBOs()) return;		
		preDraw();
		
		applyProperties();
		draw();		
		
		postDraw();
	}
	
	private boolean checkVBOs() {
		if(!vboUpdateNeeded && !needsVBOids) {
			isVboUpdating = false;
			return isVboUpdating;
		}
		
		if(needsVBOids) {
			vboUpdateNeeded = true;
		}
		
		if(vboUpdateNeeded && !isVboUpdating) {
			isVboUpdating = true;
//			Logger.println(name + " is getting buffers");
			Threads.VBOThread.addRenderable(this);
		}
		
		return isVboUpdating;
	}
	
	private void preDraw() {
		if(shader != null) {shader.bind();} else {Shader.unbind();}
		if(texture != null) {glEnable(GL_TEXTURE_2D); texture.bind();}
	}
	
	private void applyProperties() {
		glTranslatef(center.getX(), center.getY(), center.getZ());

		glRotatef(rotation.getX(), 1, 0, 0);
		glRotatef(rotation.getY(), 0, 1, 0);
		glRotatef(rotation.getZ(), 0, 0, 1);

		glTranslatef(translation.getX(), translation.getY(), translation.getZ());		
		glScalef(scale.getX(), scale.getY(), scale.getZ());
	}
	
	public abstract void draw();
	
	public abstract void updateVBOs();
	public abstract void genVBOids();
	
	private void postDraw() {
		if(shader != null) {Shader.unbind();}
		if(texture != null) {glDisable(GL_TEXTURE_2D); Texture.unbind();}
	}
	
	protected abstract void cleanUp_Renderable();
	
	public void cleanUp() {
		cleanUp_Renderable();
		
		if(vertexVBO != -1)  {ENGINE.getGLContext().deleteBuffer(vertexVBO);}
		if(textureVBO != -1) {ENGINE.getGLContext().deleteBuffer(textureVBO);}
		if(colorVBO != -1) 	 {ENGINE.getGLContext().deleteBuffer(colorVBO);}
	}
	
	public Shader 	getShader()  {return shader;}	
	public Texture 	getTexture() {return texture;}
	public Vector3f getColor() 	 {return color;}

	public Vector3f getCenter() {
		return center;
	}
	
	public Vector3f getRotation() {
		return rotation;
	}

	public Vector3f getTranslation() {
		return translation;
	}

	public Vector3f getScale() {
		return scale;
	}
	
	public boolean isVboUpdateNeeded() {
		return vboUpdateNeeded;
	}
	
	public boolean needsVBOids() {
		return needsVBOids;
	}
	
	public String getName() {
		return name;
	}
	
	public void setCenter(Vector3f center) {
		this.center = center;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public void setTranslation(Vector3f translation) {
		this.translation = translation;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}

	public void forceVBOUpdate() {
		vboUpdateNeeded = true;
	}
	
	public void setShader(Shader shader) {
		this.shader = shader;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
		vboUpdateNeeded = true;
	}

	public void setColor(Vector3f color) {
		this.color = color;
		vboUpdateNeeded = true;
	}
	
	public void stopVboUpdating() {
		isVboUpdating = false;
	}
}
