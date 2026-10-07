package com.cherryvibes.mixin;
import com.cherryvibes.render.CherryVibesBackground;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.LoadingOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(LoadingOverlay.class)
public abstract class LoadingOverlayMixin {
 @Inject(method="extractRenderState", at=@At("HEAD"))
 private void cherryVibes$loading(GuiGraphicsExtractor graphics,int mouseX,int mouseY,float delta,CallbackInfo ci){var w=Minecraft.getInstance().getWindow(); CherryVibesBackground.draw(graphics,w.getGuiScaledWidth(),w.getGuiScaledHeight());}
}
