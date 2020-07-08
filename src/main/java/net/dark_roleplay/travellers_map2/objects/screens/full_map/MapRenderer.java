package net.dark_roleplay.travellers_map2.objects.screens.full_map;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.dark_roleplay.travellers_map.api.util.MapRenderInfo;
import net.dark_roleplay.travellers_map.mapping.tickets.RenderTicket;
import net.dark_roleplay.travellers_map.util.MapManager;
import net.dark_roleplay.travellers_map.util.MapSegment;
import net.minecraft.client.gui.AbstractGui;

public class MapRenderer {

	public void renderMap(MatrixStack matrix, MapRenderInfo renderInfo){
		Long[][] maps = renderInfo.getSegments();

		for(int x = 0; x < maps.length; x++){
			for(int z = 0; z < maps[x].length; z++){
				MapSegment map = MapManager.getMapOrTryLoad(maps[x][z]);
				RenderTicket ticket = RenderTicket.getOrCreateTicket(map.getSegX(), map.getSegZ());
				map.addTicket(ticket);

				drawSegment(matrix, map, renderInfo.getOffsetX() + (x * 512), renderInfo.getOffsetZ() + (z * 512));
			}
		}
	}

	private void drawSegment(MatrixStack matrix, MapSegment map, int offsetX, int offsetZ){
		if(map != null && !map.isEmpty()) {
			map.getDynTexture().bindTexture();
			AbstractGui.blit(matrix, offsetX, offsetZ, 512, 512, 0, 0, 1, 1, 1, 1);
		}
	}
}
