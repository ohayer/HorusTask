package task.horus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private  List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        for (Block block : blocks) {
            if (block.getColor().equals(color)) {
                return Optional.of(block);
            }
            if (block instanceof CompositeBlock) {
                for (Block compositeBlock : ((CompositeBlock) block).getBlocks()) {
                    if (compositeBlock.getColor().equals(color)) {
                        return Optional.of(compositeBlock);
                    }
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> blocksWithMaterial = new ArrayList<>();
        for (Block block : blocks) {
            if (block.getMaterial().equals(material)) {
                blocksWithMaterial.add(block);
            }
            if (block instanceof CompositeBlock) {
                for (Block compositeBlock : ((CompositeBlock) block).getBlocks()) {
                    if (compositeBlock.getMaterial().equals(material)) {
                        blocksWithMaterial.add(compositeBlock);
                    }
                }
            }
        }
        return blocksWithMaterial;
    }

    @Override
    public int count() {
        int result = 0;
        for (Block block : blocks) {
            if (block instanceof CompositeBlock compositeBlocks) {
                result += compositeBlocks.getBlocks().size();
            } else {
                result++;
            }
        }
        return result;
    }
}
