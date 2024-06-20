using Sandwich.Entities.Entity;

namespace Sandwich.Contracts;
using Sandwich.Entities;

public interface ISandwichRepository
{
    /*IQueryable<Sandwiches> GetAll();*/
    
    
    Sandwiches save(Sandwiches s);


    public IQueryable<Sandwiches> findAll();


}