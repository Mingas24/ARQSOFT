using Microsoft.EntityFrameworkCore;
using Sandwich.Entities.Context;
using Sandwich.Entities.DTO;
using Sandwich.Entities.Entity;
using Sandwich.Service;

namespace Sandwich.Controllers;

public class SandwichQueryController
{
    public async Task<List<Sandwiches>> AllSandwichAsync([Service] SandwichDbContext context)
    {
        return await context.Sandwiches.ToListAsync();
    }

    public async Task<Sandwiches?> SandwichByIdAsync([Service] SandwichDbContext service, int sandwichId)
    {
        return await service.Sandwiches.FindAsync(sandwichId); 
    } 
}