package com.cherryvibes.render;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

public final class CherryVibesBackground {
    private static final Identifier BACKGROUND = Identifier.fromNamespaceAndPath("cherryvibes", "textures/cherry_night.png");
    private static final int TEX_W = 1280, TEX_H = 720;
    private CherryVibesBackground() {}

    public static void draw(GuiGraphicsExtractor graphics, int width, int height) {
        float t = System.nanoTime() / 1_000_000_000f;
        int dx = Math.round((float)Math.sin(t * 0.035f) * 5f);
        graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, dx, 0, 0, 0, width + 8, height, TEX_W, TEX_H);
        graphics.fill(0, 0, width, height, 0x220A0714);
        drawGlow(graphics, width, height, t);
        drawPetals(graphics, width, height, t);
        drawVignette(graphics, width, height);
    }

    private static void drawGlow(GuiGraphicsExtractor g, int w, int h, float t) {
        for (int i = 0; i < 7; i++) {
            float phase = t * (0.18f + i * 0.013f) + i * 1.7f;
            int x = (int)((Math.sin(phase) * 0.18f + 0.5f) * w);
            int y = (int)((Math.cos(phase * 0.7f) * 0.18f + 0.48f) * h);
            int r = 24 + (i % 3) * 12;
            int alpha = 18 + (int)(10 * Math.sin(phase * 1.4f));
            g.fill(x-r, y-r, x+r, y+r, (alpha << 24) | 0xE6A6D8);
        }
    }

    private static void drawPetals(GuiGraphicsExtractor g, int w, int h, float t) {
        for (int i = 0; i < 28; i++) {
            float speed = 8f + (i % 5) * 2.5f;
            float x0 = ((i * 97) % 113) / 113f * w;
            float y = (float)(((i * 53) % 127) / 127f * h + t * speed) % (h + 30) - 15;
            float sway = (float)Math.sin(t * 0.7f + i * 1.9f) * (7 + i % 4 * 3);
            int x = Math.round(x0 + sway), yy = Math.round(y), size = 2 + i % 3;
            int alpha = 80 + (i % 5) * 18;
            g.fill(x, yy, x+size, yy+size, (alpha << 24) | (i % 2 == 0 ? 0xFFD6EE : 0xE7A6D9));
        }
    }

    private static void drawVignette(GuiGraphicsExtractor g, int w, int h) {
        int edge = Math.max(20, Math.min(w, h) / 8);
        g.fill(0, 0, w, edge, 0x26000000);
        g.fill(0, h-edge, w, h, 0x30000000);
        g.fill(0, 0, edge, h, 0x24000000);
        g.fill(w-edge, 0, w, h, 0x24000000);
    }
}
