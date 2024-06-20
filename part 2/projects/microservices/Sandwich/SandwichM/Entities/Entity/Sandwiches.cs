using System.ComponentModel.DataAnnotations;

namespace Sandwich.Entities.Entity;

public class Sandwiches
{
    [Key]
    public int SandwichId { get; private set; }
    
    public String? SandwichPrice { get; private set; }
    
    public String? SandwichDescription { get; private set; }
    
    public String? SandwichDesignation { get; private set; }
    
    public Sandwiches(int sandwichId, string sandwichPrice, string sandwichDescription, string sandwichDesignation)
    {
        this.SandwichId = sandwichId;
        this.SandwichPrice = sandwichPrice;
        this.SandwichDescription = sandwichDescription;
        this.SandwichDesignation = sandwichDesignation;
    }

    public Sandwiches()
    {
        
    }
}