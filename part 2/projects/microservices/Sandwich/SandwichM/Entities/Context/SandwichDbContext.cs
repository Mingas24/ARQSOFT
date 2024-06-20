using Microsoft.EntityFrameworkCore;
using Sandwich.Entities.Entity;

namespace Sandwich.Entities.Context;

public class SandwichDbContext : DbContext
{
    public SandwichDbContext(DbContextOptions<SandwichDbContext> options)
        : base(options)
    { }
    
    public SandwichDbContext()
    { }

    public DbSet<Sandwiches> Sandwiches { get; set; }
        
    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
            
        if (!optionsBuilder.IsConfigured)
        {
            const String connectionString = "Server=localhost;Port=3306;Database=sandwichdb;Uid=admin;Pwd=admin2223";
            optionsBuilder.UseMySql(connectionString, new MySqlServerVersion(new Version(8, 0, 31)));
        }
            
    } 
}