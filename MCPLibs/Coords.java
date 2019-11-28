package me.kkeoki.mods.impl;

import me.kkeoki.gui.hud.ScreenPosition;
import me.kkeoki.mods.ModDraggable;

/**
 * This is an adapted version of Eric Golde's coords mod in his tutorial.
 * I simply added a direction HUD and a horizontal coordinates display.
 * You can find the series at https://bit.ly/2rzjtiu
 */
public class Coords extends ModDraggable {

    private ScreenPosition pos;

    @Override
    public int getWidth() {
        return font.getStringWidth(getCoordsPlaceholder());
    }

    private String getCoordsPlaceholder() {
        // max placeholder value (in theory)
        return "Y: 10000      NE";
    }

    @Override
    public int getHeight() {
        return (font.FONT_HEIGHT * 4) -2;
    }

    @Override
    public void render(ScreenPosition pos) {
        font.drawStringWithShadow(String.format("X: %.0f", mc.renderViewEntity.posX), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
        font.drawStringWithShadow(String.format("Y: %.0f", mc.renderViewEntity.posY), pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT + 2, -1);
        font.drawStringWithShadow(String.format("Z: %.0f", mc.renderViewEntity.posZ), pos.getAbsoluteX(), pos.getAbsoluteY() + (font.FONT_HEIGHT * 2) + 4, -1);
        String direction = "";
        switch (getDirectionFacing()) {
            case 0:
                direction = "S";
                break;
            case 1:
                direction = "SW";
                break;
            case 2:
                direction = "W";
                break;
            case 3:
                direction = "NW";
                break;
            case 4:
                direction = "N";
                break;
            case 5:
                direction = "NE";
                break;
            case 6:
                direction = "E";
                break;
            case 7:
                direction = "SE";
                break;
            default:
                break;
        }
        font.drawStringWithShadow(direction, pos.getAbsoluteX() + 54, pos.getAbsoluteY() + font.FONT_HEIGHT + 2, -1);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        // immature code > any other kinda code
        font.drawStringWithShadow("X: 69", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
        font.drawStringWithShadow("Y: 96", pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT + 2, -1);
        font.drawStringWithShadow("X: 69", pos.getAbsoluteX(), pos.getAbsoluteY() + (font.FONT_HEIGHT * 2) + 4, -1);
        font.drawStringWithShadow("N", pos.getAbsoluteX() + 54, pos.getAbsoluteY() + font.FONT_HEIGHT + 2, -1);
    }

    @Override
    public void save(ScreenPosition pos) {
        this.pos = pos;
    }

    @Override
    public ScreenPosition load() {
        return pos;
    }

    private int getDirectionFacing() {
        int yaw = (int) mc.renderViewEntity.rotationYaw;
        yaw += 360;
        yaw += 22;
        yaw %= 360;
        return yaw / 45;
    }

}