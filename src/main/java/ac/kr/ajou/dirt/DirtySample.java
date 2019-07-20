package ac.kr.ajou.dirt;

class DirtySample {
    private final UpdateSellln updateSellln = new UpdateSellln(this);
    Item[] items;

    public DirtySample(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!compare_name(items[i], "Aged Brie") && !compare_name(items[i], "Backstage passes to a TAFKAL80ETC concert"))
            {
                DecreaseQualityWhenQualityisPositiveandnameisnotSulfuras(items[i]);
            }
            else {
                if (items[i].quality < 50) {
                    increaseQuality(i);
                    if (compare_name(items[i], "Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            increaseQuality(i);
                        }
                        if (items[i].sellIn < 6) { //위 조건하고 동일 if문 전체삭제가능
                            increaseQuality(i);
                        }
                    }
                }
            }
            updateSellln.updateSellln(items[i]);
            if (items[i].sellIn < 0) {
                if (!compare_name(items[i], "Aged Brie")) {
                    if (!compare_name(items[i], "Backstage passes to a TAFKAL80ETC concert")) {
                        DecreaseQualityWhenQualityisPositiveandnameisnotSulfuras(items[i]);
                    } else {
                        items[i].quality = 0;
                    }
                } else { // selling이 0보다 작고 이름이 aged brie 이고 퀄리티가 50보다작으면 퀄리티늘림
                    if (items[i].quality < 50) {
                        increaseQuality(i);
                    }
                }
            }
        }
    }

    private void increaseQuality(int i) {
        items[i].quality = items[i].quality + 1;
    }

    private void DecreaseQualityWhenQualityisPositiveandnameisnotSulfuras(Item item) {
        if (item.quality > 0) {
            if (!compare_name(item, "Sulfuras, Hand of Ragnaros")) {
                item.quality = item.quality - 1;
            }
        }
    }

    private void updateSellln(Item item) {
        updateSellln.updateSellln(item);
    }

    public boolean compare_name(Item item, String nametocompare) {
        return item.name.equals(nametocompare);
    }
}