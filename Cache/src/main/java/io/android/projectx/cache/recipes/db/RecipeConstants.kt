package io.android.projectx.cache.recipes.db

object RecipeConstants {

    const val TABLE_NAME = "recipes"

    const val COLUMN_RECIPE_ID = "recipe_id"

    const val COLUMN_IS_BOOKMARKED = "is_bookmarked"

    const val QUERY_RECIPES = "SELECT * FROM $TABLE_NAME"

    const val DELETE_RECIPES = "DELETE FROM $TABLE_NAME"

    const val QUERY_BOOKMARKED_RECIPES = "SELECT * FROM $TABLE_NAME " +
            "WHERE $COLUMN_IS_BOOKMARKED = 1"

    const val QUERY_UPDATE_BOOKMARK_STATUS = "UPDATE $TABLE_NAME " +
            "SET $COLUMN_IS_BOOKMARKED = :isBookmarked WHERE " +
            "$COLUMN_RECIPE_ID = :recipeId"

}