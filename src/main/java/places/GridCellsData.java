package places;

import java.util.HashMap;
import java.util.Map;

public class GridCellsData {
    Map<Long, GridCell> gridCells;

    public GridCellsData() {
        gridCells = new HashMap<Long, GridCell>();
    }

    public void addGridCell(GridCell cell) {
        gridCells.put(cell.getId(), cell);
    }

    public GridCell getGridCell(long id) {
        return gridCells.get(id);
    }

}
