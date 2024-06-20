using Sandwich.Contracts;
using Sandwich.Entities;
using Sandwich.Entities.Context;
using Sandwich.Entities.Entity;

namespace Sandwich.Repository;

public class SandwichRepository : ISandwichRepository
{
    private readonly SandwichDbContext _context;

    public SandwichRepository(SandwichDbContext context)
    {
        _context = context;
    }
    
    public Sandwiches save(Sandwiches s)
    {
        Sandwiches sandwich = _context.Sandwiches.Add(s).Entity;
        _context.SaveChanges();
        return sandwich;
    }

    public IQueryable<Sandwiches> findAll()
    {
        return _context.Sandwiches.AsQueryable();
    }
}