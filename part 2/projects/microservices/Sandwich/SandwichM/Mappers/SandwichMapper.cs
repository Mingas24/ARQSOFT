using Sandwich.Entities.DTO;
using Sandwich.Entities.Entity;

namespace Sandwich.Mappers;

public class SandwichMapper
{
    public static SandwichDTO ToDTO(Sandwiches sandwich)
    {
        var id = sandwich.SandwichId.ToString();
        var price = sandwich.SandwichPrice;
        var description = sandwich.SandwichDescription;
        var designation = sandwich.SandwichDesignation;

        return new SandwichDTO(id, price, description, designation);
    }

    public static Sandwiches ToDomain(SandwichDTO sandwichDto)
    {
        var id = int.Parse(sandwichDto.SandwichId);
        var price = sandwichDto.SandwichPrice;
        var description = sandwichDto.SandwichDescription;
        var designation = sandwichDto.SandwichDesignation;
      
        return new Sandwiches(id, price, description, designation);
    }
    
    public static IQueryable<SandwichDTO> ToDTOList(IQueryable<Sandwiches> sandwiches)
    {
        List<SandwichDTO> list = new List<SandwichDTO>();
        foreach(Sandwiches s in sandwiches)
        {
            list.Add(ToDTO(s));
        }

        return list.AsQueryable();
    }


}