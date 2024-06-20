namespace Sandwich.Entities.DTO;

public class SandwichDTO
{
    public String SandwichId { get; private set; }
    
    public String? SandwichPrice { get; private set; }
    
    public String? SandwichDescription { get; private set; }
    
    public String? SandwichDesignation { get; private set; }

    public SandwichDTO(string sandwichId, string? sandwichPrice, string? sandwichDescription, string? sandwichDesignation)
    {
        SandwichId = sandwichId;
        SandwichPrice = sandwichPrice;
        SandwichDescription = sandwichDescription;
        SandwichDesignation = sandwichDesignation;
    }
}