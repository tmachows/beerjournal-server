package com.beerjournal.breweriana.utils

import com.beerjournal.breweriana.persistence.item.Attribute
import com.beerjournal.breweriana.persistence.item.Item
import com.beerjournal.breweriana.persistence.user.User
import lombok.AccessLevel
import lombok.NoArgsConstructor
import org.bson.types.ObjectId

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class TestUtils {

    static def someUser() {
        User.builder()
                .firstName("Janusz")
                .lastName("Nowak")
                .email("nowak@piwo.pl")
                .build()
    }

    static def someItem() {
        someItem(new ObjectId())
    }

    static def someItem(ownerId) {
        Item.builder()
                .name("Butelka Ciechan")
                .brewery("Ciechan")
                .country("Polska")
                .category("butelka")
                .style("zwykłe")
                .attributes([Attribute.of("volume", 0.5 as Double)] as Set)
                .ownerId(ownerId)
                .build()
    }

    static void equalsOptionalValue(optional, value) {
        assert optional.isPresent()
        assert optional.get() == value
    }

}
