package com.example.farmaciaDrPerez.responses

data class PostProduct(
    val codigo: String,
    val name : String,
    val presentation: String,
    val purchase_price : Double,
    val sale_price : Double,
    val stock : Int,
    val location : String,
    val min_stock : Int,
    val max_stock : Int,
    val description : String,
    val image : String,
    val category_id : Int,
    val supplier_id : Int
)
/**
 * $table->id();
 *
 *             $table->string('codigo')->unique()->nullable()->index();
 *             $table->string('name');
 *             $table->string('presentation')->nullable();
 *             $table->decimal('purchase_price', 10, 2)->default(0.00);
 *             $table->decimal('sale_price', 10, 2)->default(0.00);
 *             $table->integer('stock')->default(0);
 *             $table->string('location')->nullable();
 *             $table->integer('min_stock')->default(0);
 *             $table->integer('max_stock')->nullable();
 *             $table->text('description')->nullable();
 *             $table->string('image')->nullable();
 *             $table->foreignId('category_id')->nullable()->constrained('categories', 'id', 'fk_products_category')->nullOnDelete();
 *             $table->foreignId('supplier_id')->nullable()->constrained('suppliers', 'id', 'fk_products_supplier')->nullOnDelete();
 *
 *             $table->softDeletes();
 *             $table->timestamps();
 */