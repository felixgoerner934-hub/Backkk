package com.cherryvibes.mixin;
import com.cherryvibes.render.CherryVibesBackground;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(Screen.class)
public abstract class ScreenMixin {
 @org.spongepowered.asm.mixin.Shadow protected int width;
 @org.spongepowered.asm.mixin.Shadow protected int height;
 @Inject(method="extractBackground", at=@At("HEAD"), cancellable=true)
 private void cherryVibes$replace(GuiGraphicsExtractor graphics,int mouseX,int mouseY,float delta,CallbackInfo ci){CherryVibesBackground.draw(graphics,width,height); ci.cancel();}
}
