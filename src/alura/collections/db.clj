(ns alura.collections.db)

(def order1 {:user 3 :items {:backpack {:id :backpack :quantity 1 :price-per-unit 80}}})
(def order2 {:user 1 :items {:backpack {:id :backpack :quantity 2 :price-per-unit 80}}})
(def order3 {:user 2 :items {:shirt {:id :shirt :quantity 5 :price-per-unit 20}}})
(def order4 {:user 1 :items {:shoe {:id :shoe :quantity 1}}})
(def order5 {:user 2 :items {:backpack {:id :backpack :quantity 1 :price-per-unit 80}}})

(def orders [order1 order2 order3 order4 order5])
