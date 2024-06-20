using Sandwich.Entities.Context;
using Sandwich.Entities.DTO;
using Sandwich.Entities.Entity;
using Sandwich.Service;

namespace Sandwich.Controllers;

public class SandwichMutationController
{
    public async Task<Sandwiches> SaveSandwichAsync([Service] SandwichDbContext context, Sandwiches newSandwich)
    {
        context.Sandwiches.Add(newSandwich);
        await context.SaveChangesAsync();
        return newSandwich;
    }

    public async Task<Sandwiches> UpdateSandwichAsync([Service] SandwichDbContext context, Sandwiches updateSandwich)
    {
        context.Sandwiches.Update(updateSandwich);
        await context.SaveChangesAsync();
        return updateSandwich;
    }

    public async Task<string> DeleteSandwichAsync([Service] SandwichDbContext context, int sandwichId)
    {
        var sandwichToDelete = await context.Sandwiches.FindAsync(sandwichId);
        if (sandwichToDelete == null)
        {
            return "Sandwich with ID " + sandwichId + " does not exist.";
        }

        context.Sandwiches.Remove(sandwichToDelete);
        await context.SaveChangesAsync();
        return "Sandwich with ID " + sandwichId + " was removed.";
    }
   
}